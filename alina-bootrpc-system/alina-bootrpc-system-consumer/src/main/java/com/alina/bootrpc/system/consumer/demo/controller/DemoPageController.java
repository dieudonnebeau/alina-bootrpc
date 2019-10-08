package com.alina.bootrpc.system.consumer.demo.controller;

import com.alina.bootrpc.common.core.domain.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/8 19:34
 * @description：静态资源跳转公用控制器
 * @modified By：
 * @version:     1.0
 */
@Controller
public class DemoPageController {
    private String prefix = "demo/modal";

    /**
     * 模态窗口
     */
    @RequestMapping("/demo/{folder}/{page}")
    public String page(@PathVariable String folder , @PathVariable String page )
    {
        StringBuilder builder = new StringBuilder("/demo/").append(folder).append("/").append(page);
        return builder.toString();
    }



    private final static List<UserFormModel> users = new ArrayList<UserFormModel>();
    {
        users.add(new UserFormModel(1, "1000001", "测试1", "15888888888"));
        users.add(new UserFormModel(2, "1000002", "测试2", "15666666666"));
        users.add(new UserFormModel(3, "1000003", "测试3", "15666666666"));
        users.add(new UserFormModel(4, "1000004", "测试4", "15666666666"));
        users.add(new UserFormModel(5, "1000005", "测试5", "15666666666"));
    }
    /**
     * 获取用户数据
     */
    @GetMapping("/demo/form/userModel")
    @ResponseBody
    public AjaxResult userModel()
    {
        AjaxResult ajax = new AjaxResult();

        ajax.put("code", 200);
        ajax.put("value", users);
        return ajax;
    }

    /**
     * 获取数据集合
     */
    @GetMapping("/demo/form/collection")
    @ResponseBody
    public AjaxResult collection()
    {
        String[] array = { "ruoyi 1", "ruoyi 2", "ruoyi 3", "ruoyi 4", "ruoyi 5" };
        AjaxResult ajax = new AjaxResult();
        ajax.put("value", array);
        return ajax;
    }
}

class UserFormModel
{
    /** 用户ID */
    private int userId;

    /** 用户编号 */
    private String userCode;

    /** 用户姓名 */
    private String userName;

    /** 用户手机 */
    private String userPhone;

    public UserFormModel()
    {

    }

    public UserFormModel(int userId, String userCode, String userName, String userPhone)
    {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

}
