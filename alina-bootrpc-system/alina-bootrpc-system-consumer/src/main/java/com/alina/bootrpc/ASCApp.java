package com.alina.bootrpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

@EnableConfigurationProperties
@SpringBootApplication (exclude={DataSourceAutoConfiguration.class})
@ImportResource("classpath:system-dubbo-consumer.xml")
public class ASCApp {

    public static void main(String[] args) {
        SpringApplication.run(ASCApp.class, args);
    }

}
