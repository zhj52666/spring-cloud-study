package com.zhj.springcloud.controller;

import com.zhj.springcloud.entity.CommonResult;
import com.zhj.springcloud.entity.Payment;
import com.zhj.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(Payment payment){
        log.info("传入参数为：" + payment);
        return restTemplate.postForObject(PAYMENT_URL + "/create", payment, CommonResult.class);
    }

    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(@RequestParam("id") long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/getPaymentById?id=", CommonResult.class);
    }

    @GetMapping("/get")
    public CommonResult getById(@RequestParam("id") long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/getPaymentById?id=" + id, CommonResult.class);
        if ( entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return entity.getBody();
        }
    }

    @GetMapping("/getLB")
    public CommonResult getLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-provider-payment");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(instances);
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(instance.getUri() + "/payment/getLB", CommonResult.class);
        if ( entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return CommonResult.error("调用失败！");
        }
    }

    @GetMapping("/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject(PAYMENT_URL + "/zipkin", String.class);
    }
}
