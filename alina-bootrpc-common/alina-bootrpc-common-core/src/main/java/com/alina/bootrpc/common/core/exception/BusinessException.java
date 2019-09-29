package com.alina.bootrpc.common.core.exception;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:37
 * @description：业务异常
 * @modified By：
 * @version:     1.0
 */
public class BusinessException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public BusinessException(String message)
    {
        this.message = message;
    }

    public BusinessException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
