package com.taotao.comment.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.taotao.comment.mapper.ItemCommentMapper;
import com.taotao.comment.mapper.ItemCommentTagMapper;
import com.taotao.comment.pojo.ItemComment;
import com.taotao.comment.pojo.ItemCommentTag;
import com.taotao.comment.pojo.Order;
import com.taotao.common.service.ApiService;
import com.taotao.common.service.RedisService;

@Service
public class ItemCommentService {

    @Autowired
    private ItemCommentMapper itemCommentMapper;
    
    @Autowired
    private ItemCommentTagMapper tagMapper;

    @Autowired
    private RedisService redisService;
    
    @Autowired
    private ApiService apiService;
    
    private static final ObjectMapper MAPPER=new ObjectMapper();

    private static final String key = "myzset";

    public void saveComment(ItemComment itemcomment) {

        // 从缓存获取userid
        itemcomment.setUserid(1L);
        itemcomment.setCreatedate(new Date());
        itemCommentMapper.insert(itemcomment);
        String[] tags = itemcomment.getTags().split(",");

            try {
                for (String tag : tags) { 
                    //保存字段到redis
                    this.redisService.setSortedSet(key, 1.0, tag);
                    ItemCommentTag commentTag = new ItemCommentTag();
                    commentTag.setItemid(itemcomment.getItemid());
                    commentTag.setTagname(tag);
                    Example example = new Example(ItemCommentTag.class);
                    example.createCriteria().andEqualTo("itemid", commentTag.getItemid())
                            .andEqualTo("tagname", commentTag.getTagname());
                    List<ItemCommentTag> selectByExample = tagMapper.selectByExample(example);
                    if (!selectByExample.isEmpty()) {
                        ItemCommentTag exitTag = selectByExample.get(0);
                        exitTag.setUpdatedate(new Date());
                        exitTag.setCount(exitTag.getCount() + 1);
                        tagMapper.updateByPrimaryKeySelective(exitTag);
                    } else {
                        commentTag.setCount(1L);
                        commentTag.setCreatedate(new Date());
                        commentTag.setUpdatedate(commentTag.getCreatedate());
                        tagMapper.insert(commentTag);
                    }
                   
                }
                
                //修改订单评价状态   
                Order order = new Order();  
                order.setOrderId(itemcomment.getOrderid().toString());
                order.setBuyerRate(1);
                //调用接口改变商品评价状态
                this.apiService.doPostJson("http://order.taotao.com/order/changeOrderStatus",MAPPER.writeValueAsString(order) );
            
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
    }

}
