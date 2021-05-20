package com.zhj.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhj.alibaba.myhandler.BlockHandler;
import com.zhj.springcloud.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhj
 */
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return CommonResult.success("byResource");
    }

    public CommonResult handleException(BlockException e){
        return CommonResult.error("服务正忙！");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return CommonResult.success("byUrl");
    }

    @GetMapping("/blockHandler")
    @SentinelResource(value = "blockHandler", blockHandlerClass = BlockHandler.class, blockHandler = "handlerException2")
    public CommonResult blockHandler(){
        return CommonResult.success("blockHandler");
    }

}
