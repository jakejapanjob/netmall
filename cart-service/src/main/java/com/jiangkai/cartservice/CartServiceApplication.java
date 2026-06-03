package com.jiangkai.cartservice;

import com.jiangkai.hmapi.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.jiangkai.cartservice.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.jiangkai.hmapi.client",
        "com.jiangkai.hmapi.config"
        },
        defaultConfiguration = DefaultFeignConfig.class)
public class CartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
