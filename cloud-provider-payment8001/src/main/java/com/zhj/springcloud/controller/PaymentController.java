package com.zhj.springcloud.controller;

import com.zhj.springcloud.entity.CommonResult;
import com.zhj.springcloud.entity.Payment;
import com.zhj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhj
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        log.info("传入参数为：" + payment);
        int i = paymentService.create(payment);
        log.info("*********插入结果成功数：" + i);
        String msg = "插入成功 " + serverPort;
        if (i > 0) {
            return CommonResult.success(msg);
        } else {
            return CommonResult.error("插入失败");
        }
    }

    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(@RequestParam("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info(id + " 的查询结果为" + payment);
        if (payment == null) {
            return CommonResult.error("没有对应结果 您查询的" + serverPort + "端口" + "id:" + id);
        }
        return CommonResult.success(payment);
    }

    @GetMapping("/getDiscovery")
    public CommonResult getDiscovery() {
        Map result = new HashMap();
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*******service" + service);
        }
        result.put("services", services);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("******instance" + instance);
        }
        result.put("instances", instances);
        return CommonResult.success(result);
    }
}
