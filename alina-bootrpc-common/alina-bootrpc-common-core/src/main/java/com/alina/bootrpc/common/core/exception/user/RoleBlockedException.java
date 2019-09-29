package com.alina.bootrpc.common.core.exception.user;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:38
 * @description：角色锁定异常类
 * @modified By：
 * @version:     1.0
 */
public class RoleBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public RoleBlockedException()
    {
        super("role.blocked", null);
    }
}
