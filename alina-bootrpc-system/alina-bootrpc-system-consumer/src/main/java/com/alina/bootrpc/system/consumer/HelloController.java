package com.alina.bootrpc.system.consumer;

import com.alina.bootrpc.common.core.controller.BaseController;
import com.alina.bootrpc.system.facade.ISysConfigService;
import com.alina.bootrpc.system.model.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class HelloController extends BaseController {
    @Autowired
    private ISysConfigService configService;
    @RequestMapping("/hello")
    @ResponseBody
    public List<SysConfig> helloPage(){
        List<SysConfig> list = configService.queryAll();
        return list;
    }
}
