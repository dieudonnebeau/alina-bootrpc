package com.alina.bootrpc.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@EnableConfigurationProperties
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

@SpringBootApplication
@ComponentScan("com.alina.bootrpc.system.consumer")
@ImportResource("classpath:system-dubbo-consumer.xml")
public class ASCApp {

    public static void main(String[] args) {
        SpringApplication.run(ASCApp.class, args);
    }

}
