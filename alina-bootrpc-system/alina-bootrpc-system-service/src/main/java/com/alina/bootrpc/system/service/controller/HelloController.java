package com.alina.bootrpc.system.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class HelloController{

    @RequestMapping("/hello")
    @ResponseBody
    public String helloPage(){
        return "hello demo";
    }
}
