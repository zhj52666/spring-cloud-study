package com.zhj.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.zhj.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhj
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
@DefaultProperties(defaultFallback = "paymentGlobalFallBack")
public class PaymentFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/ok")
    public String paymentOK(){
        return paymentFeignService.paymentOK();
    }

    @GetMapping("/error")
    /*@HystrixCommand(fallbackMethod = "paymentErrorHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3500")
    })  // 精确配置*/
    //@HystrixCommand
    public String paymentError() {
        return paymentFeignService.paymentError();
    }

    public String paymentErrorHandler() {
        return "System is busy, Please wait!";
    }

    // global fallback
    public String paymentGlobalFallBack() {
        return "Global 异常处理信息，请稍后再试！！！";
    }

    @GetMapping("/circuit")
    public String paymentCircuitBreaker(@RequestParam("id") Integer id) {
        return paymentFeignService.paymentCircuitBreaker(id);
    }
}
