package com.zhj.springcloud.controller;

import com.zhj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhj
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/ok")
    public String paymentOK() {
        log.info("*******ok");
        return paymentService.paymentOK();
    }

    @GetMapping("/error")
    public String paymentError() {
        log.info("*******error");
        return paymentService.paymentError();
    }
}
