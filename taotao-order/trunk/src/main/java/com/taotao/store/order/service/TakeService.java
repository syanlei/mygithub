package com.taotao.store.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.taotao.store.order.bean.TaotaoResult;
import com.taotao.store.order.mapper.TakeMapper;
import com.taotao.store.order.pojo.Take;


@Service
public class TakeService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TakeMapper takeMapper;
    /***
     * 新增
     * @param json
     * @return
     */
    public Boolean createTake(Take take) {
       

        try {
            take.setId(null);
            this.takeMapper.insertSelective(take);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /***
     * 察看
     * @param userId
     * @return
     */
    public List<Take> queryTakesById(Long userId) {
        Example example=new Example(Take.class);
        example.createCriteria().andEqualTo("userId", userId);
        example.setOrderByClause("updated DESC");
        return this.takeMapper.selectByExample(example);
    }
    /***
     * 更新
     * @param json
     * @return
     */
    public Boolean updateTake(Take take) {
        try {
            this.takeMapper.updateByPrimaryKeySelective(take);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean deleteTakeById(Long id) {
        try {
        	int key = this.takeMapper.deleteByPrimaryKey(id);
        	if(key != 0){
        		return true;
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Take queryTakesByTakeId(Long id) {
        
        return this.takeMapper.selectByPrimaryKey(id);
    }
    

}
