package com.zhj.springcloud.controller;

import com.zhj.springcloud.entity.CommonResult;
import com.zhj.springcloud.entity.Payment;
import com.zhj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        log.info("传入参数为：" + payment);
        int i = paymentService.create(payment);
        log.info("*********插入结果成功数：" + i);
        if (i > 0) {
            return CommonResult.success(i);
        } else {
            return CommonResult.error("插入失败");
        }
    }

    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(@RequestParam("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info(id + " 的查询结果为" + payment);
        if (payment == null) {
            return CommonResult.error("没有对应结果 您查询的id:" + id);
        }
        return CommonResult.success(payment);
    }
}
