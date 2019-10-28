package com.alina.bootrpc.system.consumer.system;

import java.util.List;

import com.alina.bootrpc.common.core.config.Global;
import com.alina.bootrpc.system.base.BaseController;
import com.alina.bootrpc.system.facade.ISysMenuService;
import com.alina.bootrpc.system.framework.util.ShiroUtils;
import com.alina.bootrpc.system.model.SysMenu;
import com.alina.bootrpc.system.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/9 13:52
 * @description：首页 业务处理
 * @modified By：
 * @version:     1.0
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }
}
