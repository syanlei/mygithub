package com.taotao.cart.rabbitmq.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cart.service.CartService;

/**
 * 监听消息，下单完成后需要将购物车中相应的数据删除掉。
 * 
 * @author yanlei
 *
 */
@Component
public class OrderItemDelHandler {

    @Autowired
    private CartService cartService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger LOGG = LoggerFactory.getLogger(OrderItemDelHandler.class);

   /* public void deleteRrderItem(String msg) {

        try {

            JsonNode jsonNode = MAPPER.readTree(msg);

            if (LOGG.isDebugEnabled()) {
                LOGG.debug("开始删除已购买的商品" + "用户的id为:" + jsonNode.get("userId").asLong());
            }
            // 获取用户的id
            Long userId = jsonNode.get("userId").asLong();

            JsonNode itemIds = jsonNode.get("itemIds");

            // 创建一个list集合
            List<Long> itemList = new ArrayList<Long>();

            for (int i = 0; i < itemIds.size(); i++) {
                itemList.add(Long.parseLong(itemIds.get(i).toString()));
            }

            cartService.deleteby(userId, itemList);

            if (LOGG.isDebugEnabled()) {
                LOGG.debug("删除已购买的商品成功" + "用户id为：" + userId);
            }

        } catch (Exception e) {

            LOGG.error("删除已购买的商品失败" + e);
        }

    }*/

}
