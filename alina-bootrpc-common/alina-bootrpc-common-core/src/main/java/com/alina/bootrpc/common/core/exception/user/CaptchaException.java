package com.alina.bootrpc.common.core.exception.user;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:38
 * @description：验证码错误异常类
 * @modified By：
 * @version:     1.0
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
