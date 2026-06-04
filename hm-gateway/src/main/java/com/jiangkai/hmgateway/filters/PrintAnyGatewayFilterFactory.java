package com.jiangkai.hmgateway.filters;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.ObjectInputFilter;
import java.util.List;

@Component
public class PrintAnyGatewayFilterFactory
        extends AbstractGatewayFilterFactory<PrintAnyGatewayFilterFactory.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter(new GatewayFilter(){
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                HttpHeaders headers = request.getHeaders();

                //System.out.println(config.getVariable1()+config.getVariable2()+config.getVariable3());
                return chain.filter(exchange);
            }
        },2);
    }
    @Data
    public static class Config{
        private String variable1;
        private String variable2;
        private String variable3;
    }

    public PrintAnyGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("variable1","variable2","variable3");
    }
}
