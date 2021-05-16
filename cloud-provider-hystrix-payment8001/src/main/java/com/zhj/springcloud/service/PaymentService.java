package com.zhj.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @author zhj
 */
@Service
public class PaymentService {
    public String paymentOK() {
        return "线程池：" + Thread.currentThread().getName()+" is OK";
    }

    @HystrixCommand(fallbackMethod = "paymentErrorHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentError() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName()+" is Error";
    }

    public String paymentErrorHandler() {
        return "线程池：" + Thread.currentThread().getName()+" is system busy, Please wait!";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("***id 不能为负数");
        }
        String seriaNumber = IdUtil.simpleUUID();
        return "线程池：" + Thread.currentThread().getName()+" 调用成功，流水号：" + seriaNumber;
    }

    public String paymentCircuitBreakerHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName()+" 系统正忙, 请等待!";
    }
}
