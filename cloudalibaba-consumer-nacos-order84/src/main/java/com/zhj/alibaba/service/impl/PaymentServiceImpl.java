package com.zhj.alibaba.service.impl;

import com.zhj.alibaba.service.PaymentService;
import com.zhj.springcloud.entity.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @author zhj
 */
@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public CommonResult paymentSQL(Long id) {
        return CommonResult.error("出错了呀");
    }
}
