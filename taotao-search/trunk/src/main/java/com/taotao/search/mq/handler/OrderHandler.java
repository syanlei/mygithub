package com.taotao.search.mq.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.taotao.common.service.ApiService;
import com.taotao.search.bean.Order;

@Component
public class OrderHandler {


    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ApiService apiService;
    
    @Autowired
    @Qualifier("orderHttpSolrServer")
    private HttpSolrServer httpSolrServer;

    @Value("${TAOTAO_ORDER_URL}")
    private String TAOTAO_ORDER_URL;
    
    public void handler(String msg) throws Exception {
    	
        JsonNode jsonNode = MAPPER.readTree(msg);
        Long userId = jsonNode.get("userId").asLong();
        String orderId = jsonNode.get("orderId").asText();

        // 查询订单详情
        String url = TAOTAO_ORDER_URL + "/order/query/" + orderId;
        String jsonData = this.apiService.doGet(url);
        if(null == jsonData){
            return ;
        }
        
        List<Order> orderSearchs = new ArrayList<Order>();
        
        JsonNode orders = MAPPER.readTree(jsonData);
        ArrayNode orderItems = (ArrayNode)orders.get("orderItems");
        
        for (JsonNode orderItem : orderItems) {
            Order orderSearch = new Order();
            orderSearch.setCreateTime(System.currentTimeMillis());
            orderSearch.setItemId(orderItem.get("itemId").asText());
            orderSearch.setItemTitle(orderItem.get("title").asText());
            orderSearch.setOrderId(orderId);
            orderSearch.setUserId(userId);
            
            orderSearchs.add(orderSearch);
        }
        
        
        this.httpSolrServer.addBeans(orderSearchs);
        this.httpSolrServer.commit();

    }

}
