package com.fast.miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniProjectApplication {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "Asia/Seoul");
        SpringApplication.run(MiniProjectApplication.class, args);
    }

}
