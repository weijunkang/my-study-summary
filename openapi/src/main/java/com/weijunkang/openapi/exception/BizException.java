package com.weijunkang.openapi.exception;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/8/14 10:56
 */
public class BizException extends RuntimeException {

    private long code;

    public BizException(String msg) {
        super(msg);
    }

    public BizException(long code, String message) {
        super(message);
        this.code = code;
    }
}
