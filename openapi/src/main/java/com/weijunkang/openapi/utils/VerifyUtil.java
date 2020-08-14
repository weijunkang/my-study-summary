package com.weijunkang.openapi.utils;

import com.weijunkang.openapi.vo.RequestParam;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 验证工具
 * @date 2020/8/13 14:29
 */
public class VerifyUtil {

    public final static String SIGN = "sign";
    public final static String API_NAME = "apiName";
    public final static String CHANNEL_CODE = "channelCode";
    public final static String DATA = "data";
    public final static String TIMESTAMP = "timestamp";
    public final static String PAGE_NO = "pageNo";
    public final static String PAGE_SIZE = "pageSize";

    /**
     * 接口开头匹配
     */
    public final static String API_NAME_PREFIX = "hz.partner.itemNo";


    /**
     * 把请求参数进行排序组装
     * @param parameter 请求参数类
     * @return
     */
    public static String paramOrderBy(RequestParam parameter) {
        StringBuffer str = new StringBuffer();

        str.append(API_NAME).append(parameter.getApiName())
                .append(CHANNEL_CODE).append(parameter.getChannelCode());
        if (parameter.getData() != null) {
            str.append(DATA).append(parameter.getData());
        }
        if (parameter.getPageNo() != null) {
            str.append(PAGE_NO).append(parameter.getPageNo());
        }
        if (parameter.getPageSize() != null) {
            str.append(PAGE_SIZE).append(parameter.getPageSize());
        }
        str.append(TIMESTAMP).append(parameter.getTimestamp());

        return str.toString();
    }
}
