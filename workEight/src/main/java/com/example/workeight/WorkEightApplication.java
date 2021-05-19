package com.example.workeight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan("com.example.workeight.mapper")
public class WorkEightApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkEightApplication.class, args);
    }

}
