package com.zhj.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhj.alibaba.service.PaymentService;
import com.zhj.springcloud.entity.CommonResult;
import com.zhj.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhj
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-payment-service}")
    private String serverURL;

    @GetMapping("/fallback/{id}")
    // @SentinelResource(value = "fallback", fallback = "fallbackHandler")
    @SentinelResource(value = "fallback", fallback = "fallbackHandler", blockHandler = "blockHandler"
    , exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult fallback(@PathVariable("id") Long id) {
        CommonResult result = restTemplate.getForObject(serverURL + "/paymentSQL/" + id, CommonResult.class);
        if (id == 4) {
            throw new IllegalArgumentException("非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("空指针异常！！！");
        }
        return result;
    }

    public CommonResult fallbackHandler(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        CommonResult result = CommonResult.success(payment);
        result.setMessage(e.getMessage());
        return result;
    }

    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException e) {
        Payment payment = new Payment(id, "null");
        CommonResult result = CommonResult.success(payment);
        result.setMessage(e.getMessage());
        return result;
    }

    @Resource
    private PaymentService paymentService;

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
