package com.alina.bootrpc.system.consumer.monitor;

import com.alina.bootrpc.system.framework.web.domain.Server;
import com.alina.bootrpc.system.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/25 10:21
 * @description：服务器监控
 * @modified By：
 * @version:     1.0
 */
@Controller
@RequestMapping("/monitor/server")
public class ServerController extends BaseController
{
    private String prefix = "monitor/server";

    @RequiresPermissions("monitor:server:view")
    @GetMapping()
    public String server(ModelMap mmap) throws Exception
    {
        Server server = new Server();
        server.copyTo();
        mmap.put("server", server);
        return prefix + "/server";
    }
}
