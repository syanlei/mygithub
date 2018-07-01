package com.taotao.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.taotao.common.service.ApiService;

public class PackagingUtils {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    public static String twoDataList(int i, int j, List<Map<String, Object>> queryIndexAd6) throws JsonProcessingException {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int n = i; n <= j; n++) {
            dataList.add(queryIndexAd6.get(n));
        }
        return MAPPER.writeValueAsString(dataList);
    }
    /**
     * 封装JsonString转换为jsonNode工具类
     * 
     * @param url
     * @return
     */
    public static ArrayNode jsonUtils(String url,ApiService apiService) {
        try {
            String jsonData = apiService.doGet(url);
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            ArrayNode rows = (ArrayNode) jsonNode.get("rows");
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 中央广告封装方法
     * 
     * @param url2
     * @param bigMap
     * @return
     */
    public static Map<String, Object> jsonMapData(String url, Map<String, Object> bigMap,ApiService apiService) {
        String categoryId = null;

        try {
            ArrayNode rows = jsonUtils(url, apiService);
            Map<String, Object> map2 = new HashMap<String, Object>();
            int i =1;
            for (JsonNode js : rows) {
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put("d", js.get("pic").asText());
                jsonMap.put("e", i);
                jsonMap.put("c", js.get("titleDesc").asText());
                jsonMap.put("a", js.get("id").asText());
                jsonMap.put("b", js.get("title").asText());
                jsonMap.put("f", 1);
                map2.put(js.get("id").asText(), jsonMap);
                categoryId = js.get("categoryId").asText();
            }
            bigMap.put(categoryId, map2);
            return bigMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 左侧广告
     * @param url
     * @param apiService
     * @param leftContentAndImgArray 
     * @return
     */
    public static List<String[]> leftContentAndImg(String url, ApiService apiService, List<String[]> list,int n) {
        try {
            ArrayNode rows = PackagingUtils.jsonUtils(url, apiService);
            String[] title = list.get(0);
            String[] showUrl = list.get(1);
            String[] pic = list.get(2);
           
            for (int i = 0; i < n; i++) {
                title[i] = rows.get(i).get("title").asText();
                showUrl[i] = rows.get(i).get("url").asText();
                if(StringUtils.isNotEmpty(rows.get(i).get("pic").asText())){
                    pic[0]=rows.get(i).get("pic").asText();
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
