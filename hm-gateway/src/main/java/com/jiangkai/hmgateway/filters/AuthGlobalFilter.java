package com.jiangkai.hmgateway.filters;

import com.hmall.common.exception.UnauthorizedException;
import com.jiangkai.hmgateway.config.AuthProperties;
import com.jiangkai.hmgateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final AuthProperties authProperties;
    private final JwtTool jwtTool;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取Request
        ServerHttpRequest request = exchange.getRequest();
        //2.判断是否需要拦截
        if(isExclude(request.getPath().toString())){
            return chain.filter(exchange);
        }
        //3.获取Token
        String token = null;
        List<String> authorization = request.getHeaders().get("authorization");
        if (authorization!=null && !authorization.isEmpty()){
            token = authorization.get(0);
        }
        //4.校验并解析token
        Long userId = null;
        try{
            userId = jwtTool.parseToken(token);
        } catch (UnauthorizedException e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return  response.setComplete();
        }

        //5.传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange serverWebExchange = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();
        return chain.filter(serverWebExchange);
    }

    private boolean isExclude(String path) {
        for (String excludePath : authProperties.getExcludePaths()) {
            if( antPathMatcher.match(excludePath, path)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
