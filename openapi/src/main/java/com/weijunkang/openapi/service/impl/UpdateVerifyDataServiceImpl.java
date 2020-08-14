package com.weijunkang.openapi.service.impl;

import com.google.common.collect.Maps;
import com.weijunkang.openapi.advice.InitVerifyData;
import com.weijunkang.openapi.service.UpdateVerifyDataService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 修改验证数据
 * @date 2020/8/13 17:43
 */
@Service
public class UpdateVerifyDataServiceImpl implements UpdateVerifyDataService {


    private InitVerifyData initVerifyData;

    @Override
    public void setVerifyData(InitVerifyData initVerifyData) {
        this.initVerifyData = initVerifyData;
    }

    @Override
    public void updateVerifyData() {
        Map<String, String> map = Maps.newHashMap();
        map.put("TEST2", "12345");
        map.put("TEST1", "12345");
        map.put("TEST", "12345");
        initVerifyData.updateAppMap(map);
    }
}
