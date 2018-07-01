package com.taotao.search.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.search.service.OrderSearchService;



@RequestMapping("order")
@Controller
public class OrderSearchController {

    @Autowired
    private OrderSearchService orderSearchService;

    /**
     * 搜索订单
     * 
     */
   
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> search(@RequestParam("keyWords") String keyWords,
    		@RequestParam("userId") Long userId, @RequestParam("page") Integer page,
    		@RequestParam("rows") Integer rows) {
    	System.out.println("hhhhh");
    	Map<String, Object> result = this.orderSearchService.search(keyWords, userId, page, rows);
    	if (null != result) {
    		return ResponseEntity.ok(result);
    	}
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }	
}
