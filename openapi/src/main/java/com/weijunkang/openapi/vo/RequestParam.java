package com.weijunkang.openapi.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 请求 公共参数类
 * @date 2019/11/12 17:22
 */
@Data
@Accessors(chain = true)
public class RequestParam {

    /**
     * 渠道代码, 分配给第三⽅的账户名
     */
    private String channelCode;
    /**
     *  渠道代码 的秘钥
     */
    private String key;
    /**
     * 接⼝名称
     * hz.partner.order(类).getOrderInfo(方法名)
     */
    private String apiName;
    /**
     * 时间戳,每次请求的当前时间
     */
    private String timestamp;
    /**
     * 签名串
     */
    private String sign;
    /**
     * 由 json 格 式构成，且只允许包含⼀层结构，
     * 如没有业务参数，data可以不传或者传⼊data={}
     */
    private String data;
    /**
     * 当前⻚，从1开始
     */
    private Integer pageNo;
    /**
     * 每⻚显示记录数 默认20，最⼤100
     */
    private Integer pageSize;


}
