package com.taotao.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.common.service.ApiService;
import com.taotao.common.service.RedisService;
import com.taotao.manage.pojo.Content;
import com.taotao.web.bean.ArrayContent;
import com.taotao.web.utils.PackagingUtils;

@Service
public class IndexService {

    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_MANAGE_URL}")
    private String TAOTAO_MANAGE_URL;

    @Value("${INDEX_AD1_URL}")
    private String INDEX_AD1_URL;

    @Value("${INDEX_AD2_URL}")
    private String INDEX_AD2_URL;

    // 品牌旗舰店(5层)
    @Value("${INDEX_AD_RIGHT1_URL}")
    private String INDEX_AD_RIGHT1_URL;

    @Value("${INDEX_AD_RIGHT2_URL}")
    private String INDEX_AD_RIGHT2_URL;

    @Value("${INDEX_AD_RIGHT3_URL}")
    private String INDEX_AD_RIGHT3_URL;

    @Value("${INDEX_AD_RIGHT4_URL}")
    private String INDEX_AD_RIGHT4_URL;

    @Value("${INDEX_AD_RIGHT5_URL}")
    private String INDEX_AD_RIGHT5_URL;

    @Value("${INDEX_SCROLL_URL}")
    private String INDEX_SCROLL_URL;

    // 查询中部广告URL地址
    @Value("${INDEX_CENTERAD_URL}")
    private String INDEX_CENTERAD_URL;

    @Value("${INDEX_AD12_URL}")
    private String INDEX_AD12_URL;

    @Value("${INDEX_AD13_URL}")
    private String INDEX_AD13_URL;

    @Value("${INDEX_AD14_URL}")
    private String INDEX_AD14_URL;

    @Value("${INDEX_AD15_URL}")
    private String INDEX_AD15_URL;

    @Value("${INDEX_AD16_URL}")
    private String INDEX_AD16_URL;

    @Value("${INDEX_AD3_URL}")
    private String INDEX_AD3_URL;

    @Value("${INDEX_AD4_URL}")
    private String INDEX_AD4_URL;

    @Value("${INDEX_AD10_URL}")
    private String INDEX_AD10_URL;

    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static final String REDIS_INDEX1_KEY = "TAOTAO_WEB_INDEX_AD1";

    public static final String REDIS_SCROLLAD_KEY = "TAOTAO_WEB_INDEX_SCROLLAD";

    public static final String REDIS_CENTERAD_KEY = "TAOTAO_WEB_INDEX_CENTERAD";

    public static final Integer REDIS_INDEX1_TIME = 60 * 60 * 24;

    public static final Integer REDIS_CENTERAD_TIME = 60 * 60 * 24;

    public static final Integer REDIS_SCROLLAD_TIME = 60 * 60 * 24;

    public String queryIndexAd1() {

        try {
            // 先从缓存缓存中命中
            String cacheData = this.redisService.get(REDIS_INDEX1_KEY);
            if (StringUtils.isNotEmpty(cacheData)) {
                // 命中，返回
                return cacheData;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String url = TAOTAO_MANAGE_URL + INDEX_AD1_URL;
        try {
            String jsonData = this.apiService.doGet(url);

            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

            // 解析json
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            ArrayNode rows = (ArrayNode) jsonNode.get("rows");
            for (JsonNode row : rows) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("srcB", row.get("pic").asText());
                map.put("height", 240);
                map.put("alt", row.get("title").asText());
                map.put("width", 670);
                map.put("src", map.get("srcB"));
                map.put("widthB", 550);
                map.put("href", row.get("url").asText());
                map.put("heightB", 240);
                result.add(map);
            }

            if (result.isEmpty()) {
                // 如果集合为空，说明没有查询到数据，直接返回
                return null;
            }

            String resultJson = MAPPER.writeValueAsString(result);

            try {
                // 将结果集写入到redis中
                this.redisService.set(REDIS_INDEX1_KEY, resultJson, REDIS_INDEX1_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public Object queryIndexAd2() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD2_URL;
        try {
            String easyUIResultJsonData = this.apiService.doGet(url);
            EasyUIResult easyUIResult = EasyUIResult.formatToList(easyUIResultJsonData, Content.class);
            List<Content> contents = (List<Content>) easyUIResult.getRows();
            Map<String, Object> result = new HashMap<String, Object>();
            for (Content content : contents) {
                result.put("width", 310);
                result.put("height", 70);
                result.put("src", content.getPic());
                result.put("href", content.getUrl());
                result.put("alt", content.getTitle());
                result.put("widthB", 210);
                result.put("heightB", 70);
                result.put("srcB", content.getPic());
            }
            return MAPPER.writeValueAsString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过接口查询滚动广告数据
     * 
     * @return
     */
    public String queryScroll() {

        try {
            // 先从缓存缓存中命中
            String cacheData = this.redisService.get(REDIS_SCROLLAD_KEY);
            if (StringUtils.isNotEmpty(cacheData)) {
                // 命中，返回
                return cacheData;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            // 定义URL
            String url = this.TAOTAO_MANAGE_URL + this.INDEX_SCROLL_URL;
            // 通过后台接口查询数据
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
                // 解析查到的json数据
                JsonNode jsonNode = this.MAPPER.readTree(jsonData);
                // 得到EasyUIResult的rows属性中的数据,是arrayNode集合
                ArrayNode rows = (ArrayNode) jsonNode.get("rows");
                // 遍历rows集合，把rows中的属性填充到map中
                for (JsonNode row : rows) {

                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("alt", row.get("title").asText());
                    map.put("href", row.get("url").asText());
                    map.put("index", row.get("id").asText());
                    map.put("src", row.get("pic").asText());
                    map.put("ext", "");
                    result.add(map);
                }

                if (result.isEmpty()) {
                    // 如果集合为空，说明没有查询到数据，直接返回
                    return null;
                }

                String resultJson = MAPPER.writeValueAsString(result);

                try {
                    // 将结果集写入到redis中
                    this.redisService.set(REDIS_SCROLLAD_KEY, resultJson, REDIS_SCROLLAD_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return resultJson;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询中央的广告
     * 
     * @return
     */
    public String queryCenterAD() {

        try {
            // 先从缓存缓存中命中
            String cacheData = this.redisService.get(REDIS_CENTERAD_KEY);
            if (StringUtils.isNotEmpty(cacheData)) {
                // 命中，返回
                return cacheData;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {

            Map<String, Object> finalMap = new LinkedHashMap<String, Object>();

            // url
            String bigElecUrl = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "52");
            Map<String, Object> bigElecMap = getMap(bigElecUrl);

            String smallElecUrl = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "53");
            Map<String, Object> smallElecMap = getMap(smallElecUrl);

            String kitchenUrl = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "54");
            Map<String, Object> kitchenMap = getMap(kitchenUrl);

            String phoneUrl = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "55");
            Map<String, Object> phoneMap = getMap(phoneUrl);

            String carUrl = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "56");
            Map<String, Object> carMap = getMap(carUrl);

            // 二楼中部广告查询
            String secondUrl1 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "58");
            String secondUrl2 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "59");
            String secondUrl3 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "60");
            String secondUrl4 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "61");
            String secondUrl5 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "62");

            Map<String, Object> secondMap1 = getMap(secondUrl1);
            Map<String, Object> secondMap2 = getMap(secondUrl2);
            Map<String, Object> secondMap3 = getMap(secondUrl3);
            Map<String, Object> secondMap4 = getMap(secondUrl4);
            Map<String, Object> secondMap5 = getMap(secondUrl5);

            // 3楼中部广告查询
            String thirdUrl1 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "64");
            String thirdUrl2 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "65");
            String thirdUrl3 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "66");
            String thirdUrl4 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "67");
            String thirdUrl5 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "68");

            Map<String, Object> thirdMap1 = getMap(thirdUrl1);
            Map<String, Object> thirdMap2 = getMap(thirdUrl2);
            Map<String, Object> thirdMap3 = getMap(thirdUrl3);
            Map<String, Object> thirdMap4 = getMap(thirdUrl4);
            Map<String, Object> thirdMap5 = getMap(thirdUrl5);

            // 4楼中部广告查询
            String fourthUrl1 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "70");
            String fourthUrl2 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "71");
            String fourthUrl3 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "72");
            String fourthUrl4 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "73");
            String fourthUrl5 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "74");

            Map<String, Object> fourthMap1 = getMap(fourthUrl1);
            Map<String, Object> fourthMap2 = getMap(fourthUrl2);
            Map<String, Object> fourthMap3 = getMap(fourthUrl3);
            Map<String, Object> fourthMap4 = getMap(fourthUrl4);
            Map<String, Object> fourthMap5 = getMap(fourthUrl5);

            // 5楼中部广告查询
            String fifthUrl1 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "76");
            String fifthUrl2 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "77");
            String fifthUrl3 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "78");
            String fifthUrl4 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "79");
            String fifthUrl5 = this.TAOTAO_MANAGE_URL
                    + StringUtils.replace(this.INDEX_CENTERAD_URL, "{categoryId}", "80");

            Map<String, Object> fifthMap1 = getMap(fifthUrl1);
            Map<String, Object> fifthMap2 = getMap(fifthUrl2);
            Map<String, Object> fifthMap3 = getMap(fifthUrl3);
            Map<String, Object> fifthMap4 = getMap(fifthUrl4);
            Map<String, Object> fifthMap5 = getMap(fifthUrl5);

            // 大家电map
            finalMap.put("52", bigElecMap);

            // 小家电map
            finalMap.put("53", smallElecMap);

            // 厨房用具
            finalMap.put("54", kitchenMap);

            // 手机通讯
            finalMap.put("55", phoneMap);

            // 汽车配件
            finalMap.put("56", carMap);

            // 2楼map封装
            finalMap.put("58", secondMap1);
            finalMap.put("59", secondMap2);
            finalMap.put("60", secondMap3);
            finalMap.put("61", secondMap4);
            finalMap.put("62", secondMap5);

            // 3楼map封装
            finalMap.put("64", thirdMap1);
            finalMap.put("65", thirdMap2);
            finalMap.put("66", thirdMap3);
            finalMap.put("67", thirdMap4);
            finalMap.put("68", thirdMap5);

            // 4楼map封装
            finalMap.put("70", fourthMap1);
            finalMap.put("71", fourthMap2);
            finalMap.put("72", fourthMap3);
            finalMap.put("73", fourthMap4);
            finalMap.put("74", fourthMap5);

            // 5楼map封装
            finalMap.put("76", fifthMap1);
            finalMap.put("77", fifthMap2);
            finalMap.put("78", fifthMap3);
            finalMap.put("79", fifthMap4);
            finalMap.put("80", fifthMap5);

            if (finalMap.isEmpty()) {
                // 如果集合为空，说明没有查询到数据，直接返回
                return null;
            }

            String resultJson = MAPPER.writeValueAsString(finalMap);

            try {
                // 将结果集写入到redis中
                this.redisService.set(REDIS_CENTERAD_KEY, resultJson, REDIS_CENTERAD_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultJson;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Object> getMap(String url) throws ClientProtocolException, IOException,
            JsonProcessingException {

        // 通过接口查询
        String jsonData = this.apiService.doGet(url);

        Map<String, Object> CataMap = new LinkedHashMap<String, Object>();

        // 封装map
        if (StringUtils.isNotEmpty(jsonData)) {
            // 解析查到的json数据
            JsonNode jsonNode = this.MAPPER.readTree(jsonData);
            // 得到EasyUIResult的rows属性中的数据,是arrayNode集合
            ArrayNode rows = (ArrayNode) jsonNode.get("rows");
            // 遍历rows集合，把rows中的属性填充到map中
            for (int i = 0; i < rows.size(); i++) {

                Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("d", rows.get(i).get("pic").asText());
                map.put("e", i + 1);
                map.put("c", rows.get(i).get("subTitle").asText());
                map.put("a", rows.get(i).get("id").asText());
                map.put("b", rows.get(i).get("title").asText());
                map.put("f", rows.get(i).get("url").asText());
                CataMap.put(i + 1 + "", map);
            }

        }
        return CataMap;
    }

    public List<String[]> leftContentAndImg1() {
        String url;
        List<String[]> list;
        try {
            url = TAOTAO_MANAGE_URL + INDEX_AD12_URL;
            String[] title = new String[16];
            String[] showUrl = new String[16];
            String[] pic = new String[1];
            list = new ArrayList<String[]>();
            list.add(title);
            list.add(showUrl);
            list.add(pic);
            int i = 8;
            return PackagingUtils.leftContentAndImg(url, apiService, list, i);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public List<String[]> leftContentAndImg2() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD13_URL;
        List<String[]> list;
        try {
            String[] title = new String[16];
            String[] showUrl = new String[16];
            String[] pic = new String[1];
            list = new ArrayList<String[]>();
            list.add(title);
            list.add(showUrl);
            list.add(pic);
            int i = 8;
            return PackagingUtils.leftContentAndImg(url, apiService, list, i);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public List<String[]> leftContentAndImg3() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD14_URL;
        List<String[]> list;
        try {
            String[] title = new String[16];
            String[] showUrl = new String[16];
            String[] pic = new String[1];
            list = new ArrayList<String[]>();
            list.add(title);
            list.add(showUrl);
            list.add(pic);
            int i = 8;
            return PackagingUtils.leftContentAndImg(url, apiService, list, i);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public List<String[]> leftContentAndImg4() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD15_URL;
        List<String[]> list;
        try {
            String[] title = new String[16];
            String[] showUrl = new String[16];
            String[] pic = new String[1];
            list = new ArrayList<String[]>();
            list.add(title);
            list.add(showUrl);
            list.add(pic);
            int i = 8;
            return PackagingUtils.leftContentAndImg(url, apiService, list, i);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public List<String[]> leftContentAndImg5() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD16_URL;
        List<String[]> list;
        try {
            String[] title = new String[16];
            String[] showUrl = new String[16];
            String[] pic = new String[1];
            list = new ArrayList<String[]>();
            list.add(title);
            list.add(showUrl);
            list.add(pic);
            int i = 8;
            return PackagingUtils.leftContentAndImg(url, apiService, list, i);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 淘淘快报
     * 
     * @return
     */
    public Map<String, Object> queryIndexAd4() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD4_URL;
        try {
            String doGet = this.apiService.doGet(url);
            EasyUIResult easyUIResult = EasyUIResult.formatToList(doGet, Content.class);
            List<Content> contents = (List<Content>) easyUIResult.getRows();
            Map<String, Object> kuaibao = new HashMap<String, Object>();
            int i = 1;
            for (Content content : contents) {
                kuaibao.put("title" + i, content.getTitle());
                kuaibao.put("url" + i, content.getUrl());
                i++;
            }

            return kuaibao;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 导航栏
    public ArrayContent queryIndexAd3() {

        String url = TAOTAO_MANAGE_URL + INDEX_AD3_URL;
        // String[] array = new String[6];

        try {

            ArrayNode rows = PackagingUtils.jsonUtils(url, apiService);
            ArrayContent arrayTools = new ArrayContent();

            // List<JsonNode> array2 = array(rows);
            for (int i = 0; i < 6; i++) {
                arrayTools.title[i] = rows.get(i).get("title").asText();
                arrayTools.url[i] = rows.get(i).get("url").asText();
            }

            return arrayTools;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 右侧品牌旗舰店
     * 
     */
    @SuppressWarnings("unchecked")
    public Object queryIndexAdRight1() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD_RIGHT1_URL;
        try {
            String easyUIResultJsonData = this.apiService.doGet(url);
            EasyUIResult easyUIResult = EasyUIResult.formatToList(easyUIResultJsonData, Content.class);
            List<Content> contents = (List<Content>) easyUIResult.getRows();
            return contents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public Object queryIndexAdRight2() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD_RIGHT2_URL;
        try {
            String easyUIResultJsonData = this.apiService.doGet(url);
            EasyUIResult easyUIResult = EasyUIResult.formatToList(easyUIResultJsonData, Content.class);
            List<Content> contents = (List<Content>) easyUIResult.getRows();
            return contents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public Object queryIndexAdRight3() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD_RIGHT3_URL;
        try {
            String easyUIResultJsonData = this.apiService.doGet(url);
            EasyUIResult easyUIResult = EasyUIResult.formatToList(easyUIResultJsonData, Content.class);
            List<Content> contents = (List<Content>) easyUIResult.getRows();
            return contents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public Object queryIndexAdRight4() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD_RIGHT4_URL;
        try {
            String easyUIResultJsonData = this.apiService.doGet(url);
            EasyUIResult easyUIResult = EasyUIResult.formatToList(easyUIResultJsonData, Content.class);
            List<Content> contents = (List<Content>) easyUIResult.getRows();
            return contents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public Object queryIndexAdRight5() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD_RIGHT5_URL;
        try {
            String easyUIResultJsonData = this.apiService.doGet(url);
            EasyUIResult easyUIResult = EasyUIResult.formatToList(easyUIResultJsonData, Content.class);
            List<Content> contents = (List<Content>) easyUIResult.getRows();
            return contents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 右下角小图片
     * 
     * @return
     */
    public List<Map<String, Object>> queryIndexAd6() {
        String url = TAOTAO_MANAGE_URL + INDEX_AD10_URL;
        try {
            ArrayNode rows = PackagingUtils.jsonUtils(url, apiService);
            List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
            for (JsonNode row : rows) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("width", 473);
                map.put("height", 180);
                map.put("src", row.get("pic").asText());
                map.put("href", row.get("url").asText());
                map.put("alt", row.get("title").asText());
                listData.add(map);
            }
            return listData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
