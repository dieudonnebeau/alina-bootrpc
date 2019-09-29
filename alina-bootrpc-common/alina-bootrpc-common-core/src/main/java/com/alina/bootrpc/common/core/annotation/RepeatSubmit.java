package com.alina.bootrpc.common.core.annotation;

import java.lang.annotation.*;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/19 17:07
 * @description：自定义注解防止表单重复提交
 * @modified By：
 * @version:     1.0
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit
{

}