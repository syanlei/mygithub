package com.taotao.cart.handler;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cart.mapper.CartMapper;
import com.taotao.cart.pojo.Cart;
import com.taotao.cart.service.CartService;
import com.taotao.common.service.RedisService;
/**
 * 
 * 蒋林金  2015-09-09
 * @author Administrator
 *
 */
@Component
public class MergeItemHandler {
    
    //用来获取数据库中用户加入到购物车中的数据
    //和持久化登录后的购物车中的所有数据
    @Autowired
    private CartService cartService;
    
    //用来获取用户未登录时存在redis中的数据
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private CartMapper cartMapper;
    
    private static final ObjectMapper MAPPER=new ObjectMapper();
    
    private static final String REDIS_KEY = "TAOTAO_FUTURE_CART_AK47_";
    
    /**
     * 用来监听mq队列发来的消息，完成数据的合并 并持久化
     * @param jsonData
     */
   /* public void execute(String jsonData){
        if(jsonData==null){
            return;
        }
        JsonNode jsonNode = null;
        try {
            jsonNode = MAPPER.readTree(jsonData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到cookie中的key
        String redis_key=jsonNode.get("key").asText();
        if(redis_key.equalsIgnoreCase("FALSE")){
            return;
        }
        redis_key = REDIS_KEY + redis_key;
        //得到用户登录后的userId
        Long userId=jsonNode.get("userId").asLong();
        //获取缓存中的数据
        Map<String, String> mapCarts = null;
        try {
            mapCarts = redisService.hGetAll(redis_key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(null ==mapCarts || mapCarts.size()<0){
            return;
        }
        
        //遍历缓存中的数据
        for (Map.Entry<String, String> entry : mapCarts.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Cart cart = null;
            try {
                cart = MAPPER.readValue(value, Cart.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            //从数据库中查找userId itemId对应的数据
            Cart param = new Cart();
            param.setUserId(userId);
            param.setItemId(Long.valueOf(key));
            Cart oldCart = this.cartMapper.selectOne(param);    
            
            //如果数据库中没有，则插入到数据库
            if(oldCart==null){
                Cart newCart=cart;
                newCart.setUserId(userId);
                newCart.setUpdated(new Date());
                this.cartMapper.insert(newCart);
            }else{
                //如果数据库中有则要修改数量后更新
                oldCart.setNum(oldCart.getNum()+cart.getNum());
                this.cartMapper.updateByPrimaryKey(oldCart);
            }
        }
        this.redisService.del(redis_key);
    }*/
}
