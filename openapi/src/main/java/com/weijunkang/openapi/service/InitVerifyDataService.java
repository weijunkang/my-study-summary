package com.weijunkang.openapi.service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author weijunkang
 * @description: 初始化验证数据
 * @date 2020/8/13 17:40
 */
public interface InitVerifyDataService {

    /**
     * 获取所有 需要调用接口的项目信息
     * @return
     */
    Map<String, String> getAllAppMap();

    /**
     * 获取对外开发的接口的方法名集合
     * @return
     */
    List<Method> getMethodNameList();
}
