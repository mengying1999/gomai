package com.gomai;


import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwaggerButler
@MapperScan("com.gomai.goods.mapper")
public class GmGoodsService {
    public static void main(String[] args) {
        SpringApplication.run(GmGoodsService.class,args);
    }
}
