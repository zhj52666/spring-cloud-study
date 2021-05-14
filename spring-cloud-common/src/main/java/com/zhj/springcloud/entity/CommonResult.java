package com.zhj.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public CommonResult(Integer code, String message) {
        this(code, message,null);
    }

    public static CommonResult success(Object data) {
        return new CommonResult(200,"调用成功！", data);
    }

    public static CommonResult error(String message) {
        return new CommonResult(500, message,null);
    }
}
