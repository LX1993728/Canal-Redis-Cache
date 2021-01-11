package com.canal.test;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCanalClient
@SpringBootApplication
public class CanalTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalTestApplication.class, args);
    }

}
