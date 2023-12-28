package com.vere.assign_online;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan(basePackages = {"com.vere.assign_online.mapper"})
@SpringBootApplication
public class AssignOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignOnlineApplication.class, args);
    }

}
