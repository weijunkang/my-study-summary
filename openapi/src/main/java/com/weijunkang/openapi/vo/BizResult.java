package com.weijunkang.openapi.vo;



import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;

import java.util.HashMap;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/8/14 10:58
 */
public class BizResult extends HashMap<String, Object> {

    /** 状态码 */
    public static final String CODE_TAG = "bizCode";

    /** 返回内容 */
    public static final String MSG_TAG = "bigMsg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 BizResult 对象，使其表示一个空消息。
     */
    public BizResult() {
    }

    /**
     * 初始化一个新创建的 BizResult 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */
    public BizResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 BizResult 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public BizResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (BeanUtil.isNotEmpty(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static BizResult success() {
        return BizResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static BizResult success(Object data) {
        return BizResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static BizResult success(String msg) {
        return BizResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static BizResult success(String msg, Object data) {
        return new BizResult(200, msg, data);
    }


    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static BizResult error(String msg) {
        return BizResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static BizResult error(String msg, Object data) {
        return new BizResult(500, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static BizResult error(int code, String msg) {
        return new BizResult(code, msg, null);
    }

}
