package com.alina.bootrpc.system.consumer.tool;

import com.alina.bootrpc.system.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/25 10:49
 * @description：表单构建
 * @modified By：
 * @version:     1.0
 */
@Controller
@RequestMapping("/tool/build")
public class BuildController extends BaseController
{
    private String prefix = "tool/build";

    @RequiresPermissions("tool:build:view")
    @GetMapping()
    public String build()
    {
        return prefix + "/build";
    }
}
