package com.taotao.comment.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.comment.pojo.ItemComment;
import com.taotao.comment.pojo.Result;
import com.taotao.comment.service.ItemCommentService;
import com.taotao.comment.service.ResutService;

@Controller
@RequestMapping("comment")
public class ItemCommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCommentController.class);
    @Autowired
    private ItemCommentService itemCommentService;
    
    @Autowired
    private ResutService resultService;
   

    /**
     * 前台product.js中有请求jsonp
     * 根据商品id回显数据
     * @return
     */
    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public ResponseEntity<Result> ResultComment(@PathVariable("itemId") Long itemId) {
        try {
            Result result=resultService.queryAll(itemId);
            if(null!=result){
            return ResponseEntity.ok(result);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    /**
     * 添加评价
     * @param itemcomment
     * @return
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> saveComment(@ModelAttribute("itemcomment")ItemComment itemcomment){
        try {
       
            itemCommentService.saveComment(itemcomment);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            //todo log
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @RequestMapping(value="test",method=RequestMethod.POST)
    public String test(HttpServletRequest request,HttpServletResponse response){
       Map parameterMap = request.getParameterMap();
       System.out.println(parameterMap);
       return null;
    }
}
