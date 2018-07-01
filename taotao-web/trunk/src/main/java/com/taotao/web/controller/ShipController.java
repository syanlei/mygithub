package com.taotao.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.web.bean.Take;
import com.taotao.web.bean.User;
import com.taotao.web.interceptor.UserLoginHandlerInterceptor;
import com.taotao.web.service.ShipService;
import com.taotao.web.threadlocal.UserThreadLocal;

@Controller
@RequestMapping("ship")
public class ShipController {
        
        @Autowired
        private ShipService shipService;
        
        /**
         * 跳转到编辑页面页面
         * @return
         */
        
        @RequestMapping(value="{page}",method=RequestMethod.GET)
        public String invocieElectronic(@PathVariable("page")String page){
            return page;
        }
        
        /**
         * 查询用户所有的收获信息
         * @param userId
         * @return
         */
        @ResponseBody
        @RequestMapping(value="queryAllTake",method=RequestMethod.GET)
        public String listTake(){
            User user = UserThreadLocal.get();
            Long userId = user.getId();
    
            String str= this.shipService.queryAllTake(userId);//返回JOSN格式
            if(str==null){
                str="0";
            }
            return str;
        }
        
        /**
         * 保存新的收货信息
         * @param take
         * @param user
         * @return
         */
        @RequestMapping(value="saveTake",method=RequestMethod.POST)
        public ResponseEntity<Void> saveTake(Take take){
                User user = UserThreadLocal.get();
                Long userId = user.getId();
                try {
                        take.setCreated(new Date());
                        take.setUpdated(take.getCreated());
                        Boolean bool = this.shipService.saveTake(take,userId);
                        if(bool){
                            return ResponseEntity.status(HttpStatus.CREATED).build();
                        }
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        /**
         * 点击编辑时，回显的数据
         * @param id
         * @return
         */
        @RequestMapping(value="queryTake/{id}")
        public ModelAndView queryTake(@PathVariable("id")Long id){
                
                ModelAndView mv = new ModelAndView("shipEdit");
                String jsonData = this.shipService.queryTake(id);
                mv.addObject("queryTake", jsonData);
                return mv;
        }
        
        /**
         * 修改指定的收货信息
         * @param take
         * @return
         */
        @RequestMapping(value="editTake",method=RequestMethod.PUT)
        public ResponseEntity<Void> editTake(Take take){
                try {   
                        take.setUpdated(new Date());
                        Boolean bool = this.shipService.editTake(take);
                        if(bool){
                            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                        }
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        /**
         * 删除指定的收货信息
         * @param take
         * @return
         */
        @RequestMapping(value="deleteTake",method = RequestMethod.DELETE)
        public ResponseEntity<Void> deleteTake(@RequestParam("takeId")Long takeId){
                try {
                        Boolean bool = this.shipService.deleteTake(takeId);
                        if(bool){
                            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                        }
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        /**设置为默认*/
        @RequestMapping(value="setDefalutTake",method = RequestMethod.PUT)
        public ResponseEntity<Void> setDefalut(@RequestParam("takeId")Long takeId){
            try {
                Boolean bool = this.shipService.setDefalutTake(takeId);
                if(bool){
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        
        
        
        
}

