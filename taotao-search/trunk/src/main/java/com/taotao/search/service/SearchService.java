package com.taotao.search.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.taotao.search.bean.Item;
import com.taotao.search.bean.PageInfo;

@Service
public class SearchService {

    @Autowired
    @Qualifier("taotaoHttpSolrServer")
    private HttpSolrServer httpSolrServer;

    private static final Integer PAGE_SIZE = 32;

    public PageInfo<Item> search(String keyWords, Integer page) throws Exception {
        SolrQuery solrQuery = new SolrQuery(); // 构造搜索条件
        solrQuery.setQuery("title:" + keyWords + " AND status:1"); // 搜索关键词
        solrQuery.setStart((Math.max(page, 1) - 1) * PAGE_SIZE);
        solrQuery.setRows(PAGE_SIZE);

        boolean isHighlighting = !StringUtils.equals("*", keyWords) && StringUtils.isNotEmpty(keyWords);

        if (isHighlighting) {
            // 设置高亮
            solrQuery.setHighlight(true); 
            solrQuery.addHighlightField("title");
            solrQuery.setHighlightSimplePre("<em>");
            solrQuery.setHighlightSimplePost("</em>");// 后缀
        }

        // 执行查询
        QueryResponse queryResponse = this.httpSolrServer.query(solrQuery);
        List<Item> items = queryResponse.getBeans(Item.class);
        if (isHighlighting) {
            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
                for (Item item : items) {
                    if (!highlighting.getKey().equals(item.getId().toString())) {
                        continue;
                    }
                    item.setTitle(StringUtils.join(highlighting.getValue().get("title"), ""));
                    break;
                }
            }
        }
        return new PageInfo<Item>((int) queryResponse.getResults().getNumFound(), items, PAGE_SIZE);
    }

}
