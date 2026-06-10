package com.jiangkai.hmgateway;

import com.jiangkai.hmgateway.config.AuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AuthProperties.class)
public class HmGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmGatewayApplication.class, args);
    }

}
