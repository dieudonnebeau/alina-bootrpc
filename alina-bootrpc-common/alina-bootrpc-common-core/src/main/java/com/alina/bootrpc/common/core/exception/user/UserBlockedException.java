package com.alina.bootrpc.common.core.exception.user;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:39
 * @description：用户锁定异常类
 * @modified By：
 * @version:     1.0
 */
public class UserBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserBlockedException()
    {
        super("user.blocked", null);
    }
}
