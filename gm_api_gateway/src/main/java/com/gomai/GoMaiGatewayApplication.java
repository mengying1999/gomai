package com.gomai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GoMaiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoMaiGatewayApplication.class, args);
    }
}
