package com.alina.bootrpc.system;

import com.alina.bootrpc.common.mapper.ds.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@EnableConfigurationProperties
@EnableAutoConfiguration
@Import({ DynamicDataSourceRegister.class })
@SpringBootApplication
@MapperScan(basePackages = "com.alina.bootrpc.system.mapper")
@ComponentScan("com.alina.bootrpc.system.service")
@ImportResource("classpath:system-dubbo-provider.xml")
public class ASSApp {

    public static void main(String[] args) {
        SpringApplication.run(ASSApp.class, args);
    }

}
