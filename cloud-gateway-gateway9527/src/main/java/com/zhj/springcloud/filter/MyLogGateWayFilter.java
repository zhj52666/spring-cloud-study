package com.zhj.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhj
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*************come in my filter");
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username == null) {
            log.error("************ 非法入侵 **************");
           /* exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();*/
        }
        return chain.filter(exchange);
    }

    // 数字越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
