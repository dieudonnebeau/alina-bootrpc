package com.alina.bootrpc.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/19 17:06
 * @description：Excel注解集
 * @modified By：
 * @version:     1.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excels
{
    Excel[] value();
}