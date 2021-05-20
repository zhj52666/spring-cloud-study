package com.zhj.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhj.springcloud.entity.CommonResult;

/**
 * @author zhj
 */
public class BlockHandler {
    public static CommonResult handlerException1(BlockException e){
        return CommonResult.error("blockHandler 自定义的，global handler 1");
    }

    public static CommonResult handlerException2(BlockException e){
        return CommonResult.error("blockHandler 自定义的，global handler 2");
    }
}
