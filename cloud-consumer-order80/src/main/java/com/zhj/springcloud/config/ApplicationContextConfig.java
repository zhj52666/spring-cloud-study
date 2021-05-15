package com.zhj.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhj
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced   // 自定义LoadBalanced 需要注释掉这个
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
