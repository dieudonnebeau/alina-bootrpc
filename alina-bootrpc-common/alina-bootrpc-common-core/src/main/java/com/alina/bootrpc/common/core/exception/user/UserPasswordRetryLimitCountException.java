package com.alina.bootrpc.common.core.exception.user;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:40
 * @description：用户错误记数异常类
 * @modified By：
 * @version:     1.0
 */
public class UserPasswordRetryLimitCountException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitCountException(int retryLimitCount)
    {
        super("user.password.retry.limit.count", new Object[] { retryLimitCount });
    }
}
