package com.zsc.mall1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zsc.mall1.mapper")
public class Mall1Application {

    public static void main(String[] args) {
        SpringApplication.run(Mall1Application.class, args);
    }
}
