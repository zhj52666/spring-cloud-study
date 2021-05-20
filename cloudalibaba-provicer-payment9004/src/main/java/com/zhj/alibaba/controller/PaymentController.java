package com.zhj.alibaba.controller;

import com.zhj.springcloud.entity.CommonResult;
import com.zhj.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author zhj
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> data = new HashMap<>();

    static {
        data.put(1L, new Payment(1L, "fheaduhfdiasdhfjiadfhjiayrw32o32343"));
        data.put(2L, new Payment(2L, "fheaduhxzsfsfhfjiadfhjiayrw32o32344"));
        data.put(3L, new Payment(3L, "fheaduhfdiasdhfjiacxcdfdfdffds32345"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = data.get(id);
        CommonResult success = CommonResult.success(payment);
        success.setMessage("调用成功 by" + serverPort);
        return success;
    }
}
