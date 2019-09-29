package com.alina.bootrpc.common.core.annotation;


import com.alina.bootrpc.common.core.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/19 17:04
 * @description：自定义多数据源切换注解
 * @modified By：
 * @version:     1.0
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
