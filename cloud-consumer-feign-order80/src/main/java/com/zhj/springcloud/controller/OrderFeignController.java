package com.zhj.springcloud.controller;

import com.zhj.springcloud.entity.CommonResult;
import com.zhj.springcloud.entity.Payment;
import com.zhj.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhj
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @PostMapping("/create")
    public CommonResult create(Payment payment){
        log.info("传入参数为：" + payment);
        return paymentFeignService.create(payment);
    }

    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(@RequestParam("id") long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("feign/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.paymentFeignTimeout();
    }
}
