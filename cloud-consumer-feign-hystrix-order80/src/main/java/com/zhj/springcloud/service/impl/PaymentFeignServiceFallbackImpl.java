package com.zhj.springcloud.service.impl;

import com.zhj.springcloud.service.PaymentFeignService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhj
 */
@Component
public class PaymentFeignServiceFallbackImpl implements PaymentFeignService {
    @Override
    public String paymentOK() {
        return "***** PaymentOK  FallBack";
    }

    @Override
    public String paymentError() {
        return "***** PaymentError  FallBack";
    }

    @Override
    public String paymentCircuitBreaker(@RequestParam("id") Integer id) {
        return "***** PaymentCircuit  FallBack";
    }
}
