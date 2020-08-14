package com.weijunkang.openapi.exception;

import cn.hutool.core.thread.ThreadUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 验证请求异常
 * @date 2020/8/13 14:03
 */
@Slf4j
@Getter
public class VerifyRequestException extends RuntimeException {
    private long code;

    public VerifyRequestException(String msg) {
        super(msg);
        StackTraceElement s = ThreadUtil.getStackTraceElement(4);
        log.error("{}--{}--{}-{}", msg, s.getClassName(), s.getMethodName(), s.getLineNumber());
    }

    public VerifyRequestException(long code, String message) {
        super(message);
        this.code = code;
    }
}
