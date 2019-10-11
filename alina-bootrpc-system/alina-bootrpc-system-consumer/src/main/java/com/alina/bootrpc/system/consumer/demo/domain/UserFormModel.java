package com.alina.bootrpc.system.consumer.demo.domain;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/9 9:23
 * @description：用户表单相关
 * @modified By：
 * @version:     1.0
 */
public class UserFormModel {
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
