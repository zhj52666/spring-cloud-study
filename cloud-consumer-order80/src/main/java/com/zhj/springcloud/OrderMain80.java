package com.zhj.springcloud;

import com.zhj.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author zhj
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "cloud-provider-payment", configuration = MySelfRule.class)  // 切换负载均衡模式
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
