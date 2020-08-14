package com.weijunkang.openapi.service;


import com.weijunkang.openapi.vo.ApiParam;
import com.weijunkang.openapi.vo.BizResult;

/**
 * @author weijunkang
 * @description: 办件码
 * @date 2020/8/14 10:13
 */
public interface ItemNoService {

    /**
     * 处理请求
     * @param param
     * @return
     */
    BizResult process(ApiParam param);
}
