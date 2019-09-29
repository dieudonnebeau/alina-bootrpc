package com.alina.bootrpc.common.core.exception.user;


import com.alina.bootrpc.common.core.exception.base.BaseException;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:39
 * @description：用户信息异常类
 * @modified By：
 * @version:     1.0
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
