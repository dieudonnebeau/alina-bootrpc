package com.alina.bootrpc.common.core.annotation;


import com.alina.bootrpc.common.core.enums.BusinessType;
import com.alina.bootrpc.common.core.enums.OperatorType;

import java.lang.annotation.*;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/19 17:06
 * @description：自定义操作日志记录注解
 * @modified By：
 * @version:     1.0
 */

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * 模块 
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
