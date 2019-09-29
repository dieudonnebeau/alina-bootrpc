package com.alina.bootrpc.common.core.annotation;

import java.lang.annotation.*;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/19 17:03
 * @description：数据权限过滤注解
 * @modified By：
 * @version:     1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";
}
