package com.zhj.springcloud.controller;

import com.zhj.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhj
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class PaymentFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/ok")
    public String paymentOK(){
        return paymentFeignService.paymentOK();
    }

    @GetMapping("/error")
    public String paymentError() {
        return paymentFeignService.paymentError();
    }
}
