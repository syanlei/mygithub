package com.taotao.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.web.bean.Cart;
import com.taotao.web.bean.User;
import com.taotao.web.service.CartLoginService;
import com.taotao.web.service.CartRedisService;
import com.taotao.web.threadlocal.UserThreadLocal;
import com.taotao.web.utils.CookieUtils;

@RequestMapping("cart")
@Controller
public class CartController {

    @Autowired
    private CartLoginService cartLoginService;

    /*
     * @Autowired private CartCookieService cartCookieService;
     */

    @Autowired
    private CartRedisService cartRedisService;

    private static final String COOKIE_KEY = "TAOTAO_FUTURE_CART";
    
    private static final Integer COOKIE_TIME = 60 * 60 * 24 * 30 * 3;

    @RequestMapping(value = "add/{itemId}/{num}", method = RequestMethod.GET)
    public String addItemToCart(@PathVariable("itemId") Long itemId, @PathVariable("num") Integer num,
            @CookieValue(value = COOKIE_KEY, required = false) String cartKey, HttpServletRequest request,
            HttpServletResponse response) {
        User user = UserThreadLocal.get();
        if (null == user) {
            // 未登录
            /* this.cartCookieService.addItemToCart(itemId, request, response); */
            if (cartKey == null) {
                cartKey = DigestUtils.md5Hex(itemId + "_" + System.currentTimeMillis());
                CookieUtils.setCookie(request, response, COOKIE_KEY, cartKey, COOKIE_TIME, true);
            }
            this.cartRedisService.addItemToCart(itemId, num, cartKey);
        } else {
            // 已登录
            this.cartLoginService.addItemToCart(itemId, num);
        }

        // 跳转重定向
        return "redirect:/cart/show.html";
    }

    /**
     * 查询购物车数据
     * 
     * @return
     */
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public ModelAndView showCart(@CookieValue(value = COOKIE_KEY, required = false) String cartKey) {
        ModelAndView mv = new ModelAndView("cart");
        List<Cart> cartList = null;
        User user = UserThreadLocal.get();
        if (null == user) {
            // 未登录
            /* cartList = this.cartCookieService.queryCartList(request); */
            cartList = this.cartRedisService.queryCartAll(cartKey);
        } else {
            // 已登录
            cartList = this.cartLoginService.queryCartList();
        }

        mv.addObject("cartList", cartList);
        return mv;
    }

    /**
     * 更新商品数量
     * 
     * @param itemId
     * @param num
     * @return
     */
    @RequestMapping(value = "update/num/{itemId}/{num}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateNum(@PathVariable("itemId") Long itemId,
            @PathVariable("num") Integer num,
            @CookieValue(value = COOKIE_KEY, required = false) String cartKey) {
        User user = UserThreadLocal.get();
        if (null == user) {
            // 未登录
            /* this.cartCookieService.updteNum(itemId, num, request, response); */
            this.cartRedisService.updateNum(itemId, num, cartKey);
        } else {
            // 已登录
            this.cartLoginService.updateNum(itemId, num);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 移除商品
     * 
     * @param itemId
     * @param num
     * @return
     */
    @RequestMapping(value = "delete/{itemId}", method = RequestMethod.GET)
    public String deleteCartItem(@PathVariable("itemId") Long itemId,
            @CookieValue(value = COOKIE_KEY, required = false) String cartKey) {
        User user = UserThreadLocal.get();
        if (null == user) {
            // 未登录
            /* this.cartCookieService.delete(itemId, request, response); */
            this.cartRedisService.delete(itemId, cartKey);
        } else {
            // 已登录
            this.cartLoginService.delete(itemId);
        }
        // 跳转重定向
        return "redirect:/cart/show.html";
    }

    /**
     * 批量移除商品
     * 
     * @param itemId
     * @param num
     * @return
     */
    @RequestMapping(value = "delete/{ids}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCartItems(@PathVariable("ids") String ids,
            @CookieValue(value = COOKIE_KEY, required = false) String cartKey) {
        User user = UserThreadLocal.get();

        String[] ss = StringUtils.split(ids, ",");

        try {
            if (null == user) {
                // 未登录
                /* this.cartCookieService.delete(itemId, request, response); */
                for (String itemId : ss) {
                    this.cartRedisService.delete(Long.parseLong(itemId), cartKey);
                }
            } else {
                // 已登录
                for (String itemId : ss) {
                    this.cartLoginService.delete(Long.parseLong(itemId));
                }
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

}
