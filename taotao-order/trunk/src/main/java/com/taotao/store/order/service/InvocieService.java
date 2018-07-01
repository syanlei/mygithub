package com.taotao.store.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.taotao.store.order.bean.TaotaoResult;
import com.taotao.store.order.mapper.InvocieMapper;
import com.taotao.store.order.pojo.Invocie;
import com.taotao.store.order.pojo.Order;
import com.taotao.store.order.util.ValidateUtil;

@Service
public class InvocieService {
    @Autowired
    private InvocieMapper invocieMapper;
    
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Boolean createInvocie(String json) {
        Invocie invocie = null;
        try {
            invocie = objectMapper.readValue(json, Invocie.class);
            // 校验Order对象
            //ValidateUtil.validate(invocie);
        } catch (Exception e) {
            return false;
        }

        try {
            this.invocieMapper.insertSelective(invocie);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Invocie queryInvocieByinvocieId(String invocieId) {
        return this.invocieMapper.selectByPrimaryKey(invocieId);
    }

    public List<Invocie> queryInvocieByUserId(Long userId) {
        Example example=new Example(Invocie.class);
        example.createCriteria().andEqualTo("userId", userId);
        example.setOrderByClause("updated DESC");
        return this.invocieMapper.selectByExample(example);
    }

    public List<Invocie> queryInvocieByOrderId(String orderId) {
        Example example=new Example(Invocie.class);
        example.createCriteria().andEqualTo("orderId", orderId);
        example.setOrderByClause("updated DESC");
        return this.invocieMapper.selectByExample(example);
    }

    public List<Invocie> queryInvocieBytakeId(Long takeId) {
        Example example=new Example(Invocie.class);
        example.createCriteria().andEqualTo("takeId", takeId);
        example.setOrderByClause("updated DESC");
        return this.invocieMapper.selectByExample(example);
    }
    

}
