package com.alina.bootrpc.generator;

import com.alina.bootrpc.common.mapper.ds.DynamicDataSourceRegister;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@EnableConfigurationProperties
@Import({ DynamicDataSourceRegister.class })
@SpringBootApplication
@MapperScan(basePackages = "com.alina.bootrpc.generator.mapper")
@ComponentScan("com.alina.bootrpc.generator.service")
@ImportResource("classpath:generator-dubbo-provider.xml")
public class ASGApp {

    public static void main(String[] args) {
        SpringApplication.run(ASGApp.class, args);
    }

}
