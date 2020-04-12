package com.gomai;
import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.spring.annotation.MapperScan;
@EnableDiscoveryClient
@EnableSwaggerButler
@MapperScan("com.gomai.order.mapper")
@Transactional
@SpringBootApplication
public class GmOrderService {

    public static void main(String[] args) {
        SpringApplication.run(GmOrderService.class,args);
    }
}
