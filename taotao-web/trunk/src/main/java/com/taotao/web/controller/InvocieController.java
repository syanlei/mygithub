package com.taotao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("ship")
public class InvocieController {
    
    @RequestMapping(value="{pageName}" ,method=RequestMethod.GET)
    public String register(@PathVariable("pageName")String pageName) {
        return pageName;
    }

}
