package com.alina.bootrpc.common.core.enums;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:47
 * @description：用户状态
 * @modified By：
 * @version:     1.0
 */
public enum UserStatus
{
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
