package com.alina.bootrpc.system.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 13:26
 * @description：程序注解配置
 * @modified By：
 * @version:     1.0
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.ruoyi.*.mapper")
public class ApplicationConfig
{

}
