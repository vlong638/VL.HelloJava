package com.example.vltask19;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.vltask19.mapper")
public class Vltask19Application {

    public static void main(String[] args) {
        SpringApplication.run(Vltask19Application.class, args);
    }

}
