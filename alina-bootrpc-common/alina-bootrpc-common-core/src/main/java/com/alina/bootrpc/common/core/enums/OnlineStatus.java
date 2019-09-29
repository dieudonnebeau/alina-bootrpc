package com.alina.bootrpc.common.core.enums;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:47
 * @description：用户会话
 * @modified By：
 * @version:     1.0
 */
public enum OnlineStatus
{
    /** 用户状态 */
    on_line("在线"), off_line("离线");

    private final String info;

    private OnlineStatus(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }
}
