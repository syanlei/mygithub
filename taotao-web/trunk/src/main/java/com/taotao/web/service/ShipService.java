package com.taotao.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.taotao.common.bean.HttpResult;
import com.taotao.common.service.ApiService;
import com.taotao.web.bean.Take;

@Service
public class ShipService {

        @Autowired
        private ApiService apiService;

        @Value("${TAOTAO_ORDER_URL}")
        private String TAOTAO_ORDER_URL;
        @Value("${TAKE_QUERY_ALL}")
        private String TAKE_QUERY_ALL;
        @Value("${TAKE_SAVE}")
        private String TAKE_SAVE;
        @Value("${TAKE_QUERY}")
        private String TAKE_QUERY;
        @Value("${TAKE_EDIT}")
        private String TAKE_EDIT;
        @Value("${TAKE_DELETE}")
        private String TAKE_DELETE;

        private static final ObjectMapper MAPPER = new ObjectMapper();

        public String queryAllTake(Long userId) {

                String url = TAOTAO_ORDER_URL + TAKE_QUERY_ALL + userId;

                try {
                        String jsonData = this.apiService.doGet(url);
                        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

                        JsonNode jsonNode = MAPPER.readTree(jsonData);
                       
                        ArrayNode rows = (ArrayNode) jsonNode;
                        for (JsonNode row : rows) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("id", row.get("id"));
                                map.put("name", row.get("name"));
                                map.put("phone", row.get("phone"));
                                map.put("provinceName", row.get("provinceName"));
                                map.put("cityName", row.get("cityName"));
                                map.put("countyName", row.get("countyName"));
                                map.put("townName", row.get("townName"));
                                map.put("userId", row.get("userId"));// ?
                                map.put("created", row.get("created"));
                                map.put("updated", row.get("created"));

                                result.add(map);
                        }

                        if (result.isEmpty()) {
                                return null;
                        }

                        return MAPPER.writeValueAsString(result);

                } catch (Exception e) {
                        e.printStackTrace();
                }

                return null;
        }

        public Boolean saveTake(Take take, Long userId) {
                String url = TAOTAO_ORDER_URL + TAKE_SAVE;

                try {
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("id", take.getId());
                        params.put("name", take.getName());
                        params.put("phone", take.getPhone());
                        params.put("provinceName", take.getProvinceName());
                        params.put("cityName", take.getCityName());
                        params.put("countyName", take.getCountyName());
                        params.put("townName", take.getTownName());
                        params.put("userId", userId);
                        params.put("created", take.getCreated());
                        params.put("updated", take.getUpdated());
                        HttpResult httpResult = this.apiService.doPost(url, params);
                        return httpResult.getCode() == 200;
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return false;
        }

        public String queryTake(Long id) {
                String url = TAOTAO_ORDER_URL + TAKE_QUERY + id;
                try {
                        String jsonData = this.apiService.doGet(url);
                        Map<String, Object> map = new HashMap<String, Object>();

                        JsonNode row = MAPPER.readTree(jsonData);
                        map.put("id", row.get("id"));
                        map.put("name", row.get("name"));
                        map.put("phone", row.get("phone"));
                        map.put("provinceName", row.get("provinceName"));
                        map.put("cityName", row.get("cityName"));
                        map.put("countyName", row.get("countyName"));
                        map.put("townName", row.get("townName"));
                        map.put("userId", row.get("userId"));// ?
                        map.put("created", row.get("created"));
                        map.put("updated", row.get("created"));

                        if (map.isEmpty()) {
                                return null;
                        }

                        return MAPPER.writeValueAsString(map);

                } catch (Exception e) {
                        e.printStackTrace();
                }

                return null;
        }

        public Boolean editTake(Take take) {
                String url = TAOTAO_ORDER_URL + TAKE_EDIT;

                try {
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("id", take.getId());
                        params.put("name", take.getName());
                        params.put("phone", take.getPhone());
                        params.put("provinceName", take.getProvinceName());
                        params.put("cityName", take.getCityName());
                        params.put("townName", take.getTownName());
                        params.put("created", take.getCreated());
                        params.put("updated", take.getUpdated());
                        HttpResult httpResult = this.apiService.doPost(url, params);
                        return httpResult.getCode() == 200;
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return false;
        }

        public Boolean deleteTake(Long takeId) {
                String url = TAOTAO_ORDER_URL + TAKE_DELETE + takeId;
                try {
                        HttpResult httpResult = this.apiService.doPost(url);
                        return httpResult.getCode() == 204 ;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return false;
        }
        
        /** 设置为默认地址*/
        public Boolean setDefalutTake(Long takeId) {
            String url = TAOTAO_ORDER_URL + TAKE_EDIT;

            try {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("id", takeId);
                    params.put("updated",new Date() );
                    HttpResult httpResult = this.apiService.doPost(url, params);
                    return httpResult.getCode() == 200;
            } catch (Exception e) {
                    e.printStackTrace();
            }

            return false;
        }
}

