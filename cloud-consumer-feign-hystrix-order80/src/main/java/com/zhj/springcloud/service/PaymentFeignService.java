package com.zhj.springcloud.service;

import com.zhj.springcloud.service.impl.PaymentFeignServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhj
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFeignServiceFallbackImpl.class)
public interface PaymentFeignService {

    @GetMapping("/payment/ok")
    public String paymentOK();

    @GetMapping("/payment/error")
    public String paymentError();

    @GetMapping("/payment/circuit")
    public String paymentCircuitBreaker(@RequestParam("id") Integer id);
}
