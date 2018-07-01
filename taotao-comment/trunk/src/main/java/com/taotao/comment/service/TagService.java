package com.taotao.comment.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Tuple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.taotao.comment.mapper.ItemCommentTagMapper;
import com.taotao.comment.pojo.ItemCommentTag;
import com.taotao.common.service.RedisService;

@Service
public class TagService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ItemCommentTagMapper tagMapper;
    @Autowired
    private ItemCommentTagMapper itemCommentTagMapper;
    private static final String KEY = "myzset";

    public List<ItemCommentTag> queryTagList(Long itemId) {
        Set<Tuple> set = null;
        List<ItemCommentTag> tags = new ArrayList<ItemCommentTag>();
      
            try {
                set = this.redisService.getSortedSet(KEY , 0l, 5l);
                if(set.isEmpty()){
                     Example example = new Example(ItemCommentTag.class);
                     example.createCriteria().andEqualTo("itemid", itemId);
                     example.setOrderByClause("count DESC");
                     List<ItemCommentTag> list = this.itemCommentTagMapper.selectByExample(example);
                     return list;  
   
                }
                for (Tuple tuple : set) {
                    String element = tuple.getElement();
                    ItemCommentTag itemCommentTag = new ItemCommentTag();
                    itemCommentTag.setTagname(element);
                    ItemCommentTag tag = this.tagMapper.selectOne(itemCommentTag);
                    tags.add(tag);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
       
        return tags;
   
  }
}
