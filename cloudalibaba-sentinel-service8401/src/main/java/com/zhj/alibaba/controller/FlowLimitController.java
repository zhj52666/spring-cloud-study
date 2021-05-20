package com.zhj.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhj
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        Thread.sleep(800);
        return "-----testA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName() + " test B");
        return "-----testB";
    }

    @GetMapping("/testC")
    public String testC() {
        int a = 10 / 0;
        log.info("test C 异常比例");
        return "-----test C 异常比例";
    }

    @GetMapping("/testD")
    public String testD() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("test D RT");
        return "-----testD";
    }

    @GetMapping("/testHostKey")
    @SentinelResource(value = "testHostKey", blockHandler = "deal_testHostKey",fallback = "deal_fallback_testHostKey")
    public String testHostKey(@RequestParam(value = "p1",required = false) String p1, @RequestParam(value = "p2",required = false) String p2){
        int a = 10/0;  // SentinelResource处理控制台的错误 Java 出错不管
        return "test HostKey";
    }

    public String deal_testHostKey(String p1, String p2, BlockException e){
        return "服务正忙！";
    }

    public String deal_fallback_testHostKey(String p1, String p2, BlockException e){
        return "服务出错！";
    }
}
