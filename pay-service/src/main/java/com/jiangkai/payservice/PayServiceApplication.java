package com.jiangkai.payservice;

import com.jiangkai.hmapi.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.jiangkai.payservice.mapper")
@EnableFeignClients(basePackages = {
        "com.jiangkai.hmapi.client",
        "com.jiangkai.hmapi.config"
},
        defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class PayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }

}
