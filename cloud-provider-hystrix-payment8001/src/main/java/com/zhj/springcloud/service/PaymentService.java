package com.zhj.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @author zhj
 */
@Service
public class PaymentService {
    public String paymentOK() {
        return "线程池：" + Thread.currentThread().getName()+" is OK";
    }
    public String paymentError() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName()+" is Error";
    }
}
