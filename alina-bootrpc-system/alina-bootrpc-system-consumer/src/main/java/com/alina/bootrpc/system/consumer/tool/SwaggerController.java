package com.alina.bootrpc.system.consumer.tool;

import com.alina.bootrpc.system.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/25 10:50
 * @description：swagger 接口
 * @modified By：
 * @version:     1.0
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController
{
    @RequiresPermissions("tool:swagger:view")
    @GetMapping()
    public String index()
    {
        return redirect("/swagger-ui.html");
    }
}
