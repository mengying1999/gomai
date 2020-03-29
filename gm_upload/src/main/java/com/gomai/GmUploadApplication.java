package com.gomai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GmUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(GmUploadApplication.class, args);
    }
}
