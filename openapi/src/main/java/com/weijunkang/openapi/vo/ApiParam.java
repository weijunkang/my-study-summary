package com.weijunkang.openapi.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/8/13 14:54
 */
@Data
@Accessors(chain = true)
public class ApiParam {

    /**
     * 调用的接口 名称等信息
     */
    private String apiName;
    /**
     * 请求方 取到代码
     */
    private String channelCode;
    /**
     * 请求时间戳
     */
    private String timestamp;
    /**
     * 签名
     */
    private String sign;
    /**
     * 业务参数
     */
    private String data;

}
