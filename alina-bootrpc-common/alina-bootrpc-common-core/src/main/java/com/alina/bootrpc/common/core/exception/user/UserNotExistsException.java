package com.alina.bootrpc.common.core.exception.user;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:40
 * @description：用户不存在异常类
 * @modified By：
 * @version:     1.0
 */
public class UserNotExistsException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("user.not.exists", null);
    }
}
