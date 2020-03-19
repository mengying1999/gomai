package com.gomai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GmRegistry {
    public static void main(String[] args) {
        SpringApplication.run(GmRegistry.class, args);
    }
}
