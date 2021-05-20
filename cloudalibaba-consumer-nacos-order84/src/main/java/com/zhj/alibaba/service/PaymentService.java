package com.zhj.alibaba.service;

import com.zhj.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhj
 */
@FeignClient("nacos-provider-payment")
public interface PaymentService {
    @GetMapping("/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Long id);
}
