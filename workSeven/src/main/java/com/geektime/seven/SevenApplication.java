package com.geektime.seven;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.geektime.seven.mapper")
public class SevenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenApplication.class, args);
    }

}
