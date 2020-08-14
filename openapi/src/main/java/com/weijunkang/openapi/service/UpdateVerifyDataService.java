package com.weijunkang.openapi.service;

import com.weijunkang.openapi.advice.InitVerifyData;

/**
 * @author weijunkang
 * @description: 修改验证数据
 * @date 2020/8/13 17:43
 */
public interface UpdateVerifyDataService {
    /**
     * 设置
     * @param initVerifyData
     */
    void setVerifyData(InitVerifyData initVerifyData);

    /**
     * 更新
     */
    void updateVerifyData();
}
