package com.zhj.springcloud.service;

import com.zhj.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhj
 */
public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}
