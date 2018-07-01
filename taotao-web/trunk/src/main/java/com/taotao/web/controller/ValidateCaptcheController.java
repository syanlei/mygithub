package com.taotao.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.service.RedisService;

@Controller
@RequestMapping("validate")
public class ValidateCaptcheController {
    @Autowired
    private RedisService redisService;
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> validateCapthce(@RequestParam("data")String data){
        
        try {
            String captche = this.redisService.get("CAPTCHE");
            boolean boo = data.equalsIgnoreCase(captche);
            if(boo){
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
