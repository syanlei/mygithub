package com.taotao.manage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.mapper.ContentMapper;
import com.taotao.manage.pojo.Content;

@Service
public class ContentService  extends BaseService<Content>  {

    @Autowired
    private ContentMapper contentMapper;

    /**
     * 根据分类id查询内容，并且按照更新时间倒序排序
     * 
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    public EasyUIResult queryContentList(Long categoryId, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Content> contents = this.contentMapper.queryContentListByCategoryId(categoryId);
        
       
        PageInfo<Content> pageInfo = new PageInfo<Content>(contents);
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    public void editContent(Content content) {
        this.contentMapper.updateByPrimaryKeySelective(content);
    }
  

}
