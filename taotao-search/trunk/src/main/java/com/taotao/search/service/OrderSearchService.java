package com.taotao.search.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.taotao.search.bean.Order;

@Service
public class OrderSearchService {

    @Autowired
    @Qualifier("orderHttpSolrServer")
    private HttpSolrServer httpSolrServer;

    public Map<String, Object> search(String keyWords, Long userId, Integer page, Integer rows) {

        try {
            SolrQuery solrQuery = new SolrQuery(); 
            solrQuery.setQuery("searchField:" + keyWords + " AND userId:" + userId); 
            solrQuery.setStart((Math.max(page, 1) - 1) * rows);
            solrQuery.setRows(rows);

            // 执行查询
            QueryResponse queryResponse = this.httpSolrServer.query(solrQuery);

            List<Order> items = queryResponse.getBeans(Order.class);
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("list", items);
            result.put("total", queryResponse.getResults().getNumFound());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
