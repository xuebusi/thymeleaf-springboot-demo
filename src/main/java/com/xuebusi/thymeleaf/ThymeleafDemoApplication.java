package com.xuebusi.thymeleaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xuebusi.thymeleaf.mapper")
public class ThymeleafDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThymeleafDemoApplication.class, args);
    }
}
