package com.taotao.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.service.RedisService;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.web.bean.Item;
import com.taotao.web.bean.ItemComment;
import com.taotao.web.bean.OrderItemBasicInfo;
import com.taotao.web.service.ItemService;
import com.taotao.web.utils.CookieUtils;

@RequestMapping("item")
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private RedisService redisService;

    public static final String COOKIE_TOKEN = "TAOTAO_TOKEN";
    /**
     * 商品详情页
     * 
     * @param itemId
     * @return
     */
    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public ModelAndView itemDetail(@PathVariable("itemId") Long itemId) {
        ModelAndView mv = new ModelAndView("item");
        Item item = this.itemService.queryItemById(itemId);
        //商品的基本数据
        mv.addObject("item", item);
        
        //商品的描述数据
        ItemDesc itemDesc = this.itemService.queryItemDescByItemId(itemId);
        mv.addObject("itemDesc", itemDesc);
        
        //商品的规格参数数据
        String itemParam = this.itemService.queryItemPatramItemByItemId(itemId);
        mv.addObject("itemParam", itemParam);
        
        return mv;
    }
    
    @RequestMapping(value="comment",method=RequestMethod.POST)
    public ModelAndView itemComment(OrderItemBasicInfo iteminfo){
        ModelAndView mv=new ModelAndView("my-order-comment");

        mv.addObject("iteminfo",iteminfo);
       
        return mv;
    }
    @RequestMapping(value="save",method=RequestMethod.POST)
    public ModelAndView saveComment(ItemComment itemcomment){
        ModelAndView mv=new ModelAndView("my-orders");
       
        try{
            this.itemService.saveComment(itemcomment);
        }catch(Exception e){
            e.printStackTrace();
        }        
        return mv;
    }

}
