package com.zhj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhj
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZKOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ZKOrderMain80.class, args);
    }
}
