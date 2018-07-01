package com.taotao.store.order.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.store.order.bean.TaotaoResult;
import com.taotao.store.order.pojo.Order;
import com.taotao.store.order.pojo.Take;
import com.taotao.store.order.service.TakeService;

@Controller
@RequestMapping(value="take")
public class TakeController {
    @Autowired
    private TakeService takeService;
    
    /**
     * 创建收件人信息
     * @param json
     * @return
     */
    /*@ResponseBody
    @RequestMapping(value = "create" , method = RequestMethod.POST)
    public TaotaoResult createTake(@RequestBody String json) {
            return this.takeService.createTake(json);
    }*/
    
    @ResponseBody
    @RequestMapping(value = "create" , method = RequestMethod.POST)
    public ResponseEntity<Void> createTake(Take take) {
    	try {
			Boolean bool = this.takeService.createTake(take);
			if(bool){
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 根据用户查询收件人信息
     * @param 
     * @return List<Take>
     */
    /*@ResponseBody
    @RequestMapping(value = "/query/{userId}" ,method = RequestMethod.GET)
    public List<Take> queryTakesById(@PathVariable("userId") Long userId) {
            return this.takeService.queryTakesById(userId);
    }*/
    
    
    @ResponseBody
    @RequestMapping(value = "/query/{userId}" ,method = RequestMethod.GET)
    public ResponseEntity<List<Take>> queryTakesById(@PathVariable("userId") Long userId) {
    	try {
			List<Take> listTake = this.takeService.queryTakesById(userId);
			if(null == listTake || listTake.size() == 0){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			return ResponseEntity.ok(listTake);
		} catch (Exception e) {
			e.printStackTrace();
		}
    		
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    /**
     * 根据id查询收件人信息
     * @param 
     * @return List<Take>
     */
    /*@ResponseBody
    @RequestMapping(value = "/querybyid/{id}" ,method = RequestMethod.GET)
    public Take queryTakesByTakeId(@PathVariable("id") Long id) {
            return this.takeService.queryTakesByTakeId(id);
    }*/
    
    @ResponseBody
    @RequestMapping(value = "/querybyid/{id}" ,method = RequestMethod.GET)
    public ResponseEntity<Take> queryTakesByTakeId(@PathVariable("id") Long id) {
    		try {
				Take take = this.takeService.queryTakesByTakeId(id);
				if(null == take){
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
				}
				return ResponseEntity.ok(take);
			} catch (Exception e) {
				e.printStackTrace();
			}
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    /**
     * 根据用户查询收件人信息
     * @param 
     * @return List<Take>
     */
    /*@ResponseBody
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    public TaotaoResult updateTake(@RequestBody String json) {
            return this.takeService.updateTake(json);
    }*/
    
    @ResponseBody
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    public ResponseEntity<Void> updateTake(Take take) {
    	
    	try {
			Boolean bool = this.takeService.updateTake(take);
			if(bool){
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
   /* @ResponseBody
    @RequestMapping(value = "/delete/{id}" ,method = RequestMethod.GET)
    public TaotaoResult deleteTakeById(@PathVariable("id") Long id){
        return this.takeService.deleteTakeById(id);
    }*/
    
    @ResponseBody
    @RequestMapping(value = "/delete/{id}" ,method = RequestMethod.POST)
    public ResponseEntity<Void> deleteTakeById(@PathVariable("id") Long id){
    	
    	try {
			Boolean bool = this.takeService.deleteTakeById(id);
			if(bool){
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    			
    }
    
    
    
    
    

}
