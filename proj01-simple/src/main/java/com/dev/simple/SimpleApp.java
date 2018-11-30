package com.dev.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.dev.simple.mapper")
public class SimpleApp {

    public static void main(String[] args) {
        SpringApplication.run(SimpleApp.class);
    }
}
