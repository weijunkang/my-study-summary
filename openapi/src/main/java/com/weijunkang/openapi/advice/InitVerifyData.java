package com.weijunkang.openapi.advice;


import com.weijunkang.openapi.service.InitVerifyDataService;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 存放应用方数据 和 方法数据
 * @date 2020/8/13 16:26
 */
public class InitVerifyData {

    /**
     * key: 应用方代码
     * value: 应用方的密码
     */
    private final Map<String, String> appMap = new ConcurrentHashMap<>();

    /**
     * 可以远程调用的接口方法集合
     */
    private List<Method> methodNameList = new CopyOnWriteArrayList<>();


    public InitVerifyData(InitVerifyDataService initVerifyDataService) {
        appMap.putAll(initVerifyDataService.getAllAppMap());

        //通过Class 得到类中的方法 获取所有方法 判断方法上是否有开放接口注解 把有注解的加入到methodList中
        methodNameList.addAll(initVerifyDataService.getMethodNameList());
    }

    /**
     * 通过 渠道代码 获取 对应的 key
     * @param channelCode
     * @return
     */
    public String getKey(String channelCode) {
        return appMap.get(channelCode);
    }

    /**
     * 通过 apiName 中的方法名 判断方法是否存在
     * @param methodName 方法名
     * @return
     */
    public boolean existMethod(String methodName){
        return methodNameList.stream().map(Method::getName).collect(Collectors.toList()).contains(methodName);
    }

    public boolean updateAppMap(Map<String, String> map){
        //清空appMap 在重新插入
        appMap.clear();
        appMap.putAll(map);
        return true;
    }

}
