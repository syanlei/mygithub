package com.taotao.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import com.taotao.comment.pojo.ItemCommentTag;
import com.taotao.comment.service.TagService;
@Controller
@RequestMapping("tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @RequestMapping(value = "{itemId}",method=RequestMethod.GET)
    public ResponseEntity<List<ItemCommentTag>> queryTagList(@PathVariable("itemId")Long itemId){
    try {
         List<ItemCommentTag> list = this.tagService.queryTagList(itemId) ;
       return  ResponseEntity.ok(list);
    } catch (Exception e) {
        e.printStackTrace();
    } 
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
