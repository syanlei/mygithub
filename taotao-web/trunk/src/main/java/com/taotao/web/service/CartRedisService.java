package com.taotao.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.service.RedisService;
import com.taotao.web.bean.Cart;
import com.taotao.web.bean.Item;

@Service
public class CartRedisService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RedisService redisService;


    private static final Integer REDIS_TIME = 60 * 60 * 24 * 30 * 3;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    // public static final String COOKIE_KEY = "TAOTAO_FUTURE_CART";

    private static final String REDIS_KEY = "TAOTAO_FUTURE_CART_AK47_";

    public void addItemToCart(Long itemId, Integer num, String cartKey) {

        String key = REDIS_KEY + cartKey;

        String cartValue = this.redisService.hget(key, String.valueOf(itemId));

        try {
            Cart cart = null;
            if (null == cartValue) {
                cart = new Cart();
                Item item = this.itemService.queryItemById(itemId);
                cart.setItemId(itemId);
                cart.setItemImage(item.getImages()[0]);
                cart.setItemPrice(item.getPrice());
                cart.setItemTitle(item.getTitle());
                cart.setCreated(new Date());
                cart.setNum(num);
                cart.setUpdated(cart.getCreated());
            } else {
                cart = MAPPER.readValue(cartValue, Cart.class);
                cart.setNum(cart.getNum() + num);
                cart.setUpdated(new Date());
            }
            this.redisService.hset(key, String.valueOf(itemId), MAPPER.writeValueAsString(cart), REDIS_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * public Cart queryCart(HttpServletRequest request, Long itemId) { String cookieValue =
     * CookieUtils.getCookieValue(request, COOKIE_KEY, true); if
     * (StringUtils.isNotEmpty(cookieValue)) { try { String cartDate =
     * this.redisService.hget(cookieValue, itemId.toString());
     * 
     * Cart cart = MAPPER.readValue(cartDate, Cart.class); return cart;
     * 
     * } catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
     * 
     * } return null; }
     */

 
    /**
     * 更新商品数量
     * 
     * @param itemId
     * @param num
     * @param cartKey
     */
    public void updateNum(Long itemId, Integer num, String cartKey) {
        String key = REDIS_KEY + cartKey;
        try {
            String cartData = this.redisService.hget(key, itemId.toString());
            Cart cart = MAPPER.readValue(cartData, Cart.class);
            cart.setNum(num);
            cart.setUpdated(new Date());
            this.redisService.hset(key, itemId.toString(), MAPPER.writeValueAsString(cart),
                    REDIS_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 移除商品
     * 
     * @param itemId
     * @param cartKey
     */
    public void delete(Long itemId, String cartKey) {
        String key = REDIS_KEY + cartKey;
        this.redisService.hdel(key, String.valueOf(itemId));
    }

    /**
     * 查询购物车列表
     * 
     * @param cartKey
     * @return
     */
    public List<Cart> queryCartAll(String cartKey) {
        String key = REDIS_KEY + cartKey;
        Map<String, String> mapCart = this.redisService.hGetAll(key);
        List<Cart> list = new ArrayList<Cart>(mapCart.size());
        try {
            for (Map.Entry<String, String> entry : mapCart.entrySet()) {
                list.add(MAPPER.readValue(entry.getValue(), Cart.class));
            }
            this.redisService.expire(key, REDIS_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
