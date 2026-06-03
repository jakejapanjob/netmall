package com.jiangkai;

import com.jiangkai.hmapi.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.jiangkai.userservice.mapper")
@EnableFeignClients(basePackages = {
        "com.jiangkai.hmapi.client",
        "com.jiangkai.hmapi.config"
},
        defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
