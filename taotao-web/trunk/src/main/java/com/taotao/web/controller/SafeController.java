package com.taotao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("safe")
@Controller
public class SafeController {
    
    //手机页面 
    @RequestMapping(value = "findpwd", method = RequestMethod.GET)
    public String findpwd() {
        return "findpwd";
    }
    //确认手机号
    @RequestMapping(value = "checkuser", method = RequestMethod.GET)
    public String checkuser() {
        return "checkuser";
    }
    //修改密码
    @RequestMapping(value = "newpassword", method = RequestMethod.GET)
    public String newpassword() {
        return "newpassword";
    }
    //完成界面
    @RequestMapping(value = "safefinish", method = RequestMethod.GET)
    public String safefinish() {
        return "safefinish";
    }

}
