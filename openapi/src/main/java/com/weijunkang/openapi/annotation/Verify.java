package com.weijunkang.openapi.annotation;


import java.lang.annotation.*;

/**
 * @author weijunkang
 * @description: 验证签名
 * @date 2020/8/13 13:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Verify {

    /**
     * 请求数据时间戳校验时间差
     * 超过(当前时间-指定时间)的数据认定为伪造
     * 注意应用程序需要捕获 {@link VerifyRequestException} 异常
     */
    long timeout() default 3000;
}
