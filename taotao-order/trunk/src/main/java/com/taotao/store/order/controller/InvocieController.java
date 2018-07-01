package com.taotao.store.order.controller;

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
import com.taotao.store.order.pojo.Invocie;
import com.taotao.store.order.pojo.Take;
import com.taotao.store.order.service.InvocieService;

@Controller
@RequestMapping(value="invocie")
public class InvocieController {
    @Autowired
    private InvocieService invocieService;
    
    /*@ResponseBody
    @RequestMapping(value = "create" , method = RequestMethod.POST)
    public TaotaoResult createInvocie(@RequestBody String json){
        return this.invocieService.createInvocie(json);
    }*/
    
    @ResponseBody
    @RequestMapping(value = "create" , method = RequestMethod.POST)
    public ResponseEntity<Void> createInvocie(@RequestBody String json){
    	try {
			Boolean bool = this.invocieService.createInvocie(json);
			if(bool){
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
    
    /*@ResponseBody
    @RequestMapping(value = "queryByInvocieId/{invocieId}" , method = RequestMethod.GET)
    public Invocie queryInvocieByinvocieId(@PathVariable("invocieId") long invocieId){
        return this.invocieService.queryInvocieByinvocieId(invocieId);
    }*/
    
    @ResponseBody
    @RequestMapping(value = "queryByInvocieId/{invocieId}" , method = RequestMethod.GET)
    public ResponseEntity<Invocie> queryInvocieByinvocieId(@PathVariable("invocieId") String invocieId){
        try {
        	Invocie invocie = this.invocieService.queryInvocieByinvocieId(invocieId);
        	if(null == invocie){
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        	}
			return ResponseEntity.ok(invocie);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        
    }
    
    /*@ResponseBody
    @RequestMapping(value = "queryByUserId/{userId}" , method = RequestMethod.GET)
    public List<Invocie> queryInvocieByUserId(@PathVariable("userId") Long userId){
        return this.invocieService.queryInvocieByUserId(userId);
    }*/
    
    @ResponseBody
    @RequestMapping(value = "queryByUserId/{userId}" , method = RequestMethod.GET)
    public ResponseEntity<List<Invocie>> queryInvocieByUserId(@PathVariable("userId") Long userId){
    	
        try {
        	List<Invocie> listInvocie = this.invocieService.queryInvocieByUserId(userId);
        	if(null == listInvocie || listInvocie.size() == 0){
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        	}
			return ResponseEntity.ok(listInvocie);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    	
    }
    
    /*@ResponseBody
    @RequestMapping(value = "queryByOrderId/{orderId}" , method = RequestMethod.GET)
    public List<Invocie> queryInvocieByOrderId(@PathVariable("orderId") String orderId){
        return this.invocieService.queryInvocieByOrderId(orderId);
    }*/
    
    @ResponseBody
    @RequestMapping(value = "queryByOrderId/{orderId}" , method = RequestMethod.GET)
    public ResponseEntity<List<Invocie>> queryInvocieByOrderId(@PathVariable("orderId") String orderId){
    	try {
        	List<Invocie> listInvocie = this.invocieService.queryInvocieByOrderId(orderId);
        	if(null == listInvocie || listInvocie.size() == 0){
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        	}
			return ResponseEntity.ok(listInvocie);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    /*@ResponseBody
    @RequestMapping(value = "queryBytakeId/{takeId}" , method = RequestMethod.GET)
    public List<Invocie> queryInvocieBytakeId(@PathVariable("takeId") Long takeId){
        return this.invocieService.queryInvocieBytakeId(takeId);
    }*/
    
    @ResponseBody
    @RequestMapping(value = "queryBytakeId/{takeId}" , method = RequestMethod.GET)
    public ResponseEntity<List<Invocie>> queryInvocieBytakeId(@PathVariable("takeId") Long takeId){
        
        try {
        	List<Invocie> listInvocie = this.invocieService.queryInvocieBytakeId(takeId);
			return ResponseEntity.ok(listInvocie);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
