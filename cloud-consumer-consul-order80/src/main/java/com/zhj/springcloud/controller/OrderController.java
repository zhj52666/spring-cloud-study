package com.zhj.springcloud.controller;

import com.zhj.springcloud.entity.CommonResult;
import com.zhj.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhj
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {

    private static final String PAYMENT_URL = "http://cloud-provider-payment/payment";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public CommonResult create(Payment payment){
        log.info("传入参数为：" + payment);
        return restTemplate.postForObject(PAYMENT_URL + "/create", payment, CommonResult.class);
    }

    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(@RequestParam("id") long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/getPaymentById?id=" + id, CommonResult.class);
    }
}
