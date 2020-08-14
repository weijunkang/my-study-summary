package com.weijunkang.openapi.service.impl;

import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weijunkang.openapi.annotation.OpenApi;
import com.weijunkang.openapi.service.InitVerifyDataService;
import com.weijunkang.openapi.service.ItemNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 初始化验证数据
 * @date 2020/8/13 17:40
 */
@Service
public class InitVerifyDataServiceImpl implements InitVerifyDataService {

    @Autowired
    private ItemNoService itemNoService;

    @Override
    public Map<String, String> getAllAppMap() {
        Map<String, String> map = Maps.newHashMap();
        map.put("TEST", "12345");
        map.put("TEST1", "12345");
        return map;
    }

    @Override
    public List<Method> getMethodNameList() {
        List<Method> list = Lists.newArrayList();
        //得到 私有并且有OpenApi注解的方法
        Method[] methods = ReflectUtil.getMethods(itemNoService.getClass(), m ->
                Modifier.isPrivate(m.getModifiers()) && m.isAnnotationPresent(OpenApi.class)
        );

        for (int i = 0; i < methods.length; i++) {
            list.add(methods[i]);
        }
        return list;
    }
}
