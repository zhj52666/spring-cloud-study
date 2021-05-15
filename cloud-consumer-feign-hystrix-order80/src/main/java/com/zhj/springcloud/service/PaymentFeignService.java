package com.zhj.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhj
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface PaymentFeignService {

    @GetMapping("/payment/ok")
    public String paymentOK();

    @GetMapping("/payment/error")
    public String paymentError();
}
