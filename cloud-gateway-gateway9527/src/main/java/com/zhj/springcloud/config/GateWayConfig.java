package com.zhj.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhj
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocator routes = routeLocatorBuilder.routes()
                .route("path_route_zhj01",
                        r -> r.path("/in").uri("http://www.baidu.com/in")
                )
                .route("path_route_zhj02",
                        r -> r.path("/out").uri("http://www.baidu.com/out")
                )
                .build();
        return routes;
    }
}
