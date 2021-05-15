package com.zhj.springcloud.service;

import com.zhj.springcloud.entity.CommonResult;
import com.zhj.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhj
 */
@Component
@FeignClient(value = "cloud-provider-payment")
public interface PaymentFeignService {
    @PostMapping("/payment/create")
    CommonResult create(Payment payment);
    @GetMapping("/payment/getPaymentById")
    CommonResult getPaymentById(@RequestParam("id") Long id);
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();
}
