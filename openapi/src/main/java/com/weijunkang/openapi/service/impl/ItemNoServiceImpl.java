package com.weijunkang.openapi.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import com.weijunkang.openapi.annotation.OpenApi;
import com.weijunkang.openapi.exception.BizException;
import com.weijunkang.openapi.service.ItemNoService;
import com.weijunkang.openapi.vo.ApiParam;
import com.weijunkang.openapi.vo.BizResult;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 办件码
 * @date 2020/8/14 10:13
 */
@Service
public class ItemNoServiceImpl implements ItemNoService {

    @Override
    public BizResult process(ApiParam param) {
        /**
         * 1.验证data
         * 2.获取方法名
         * 3.执行方法
         */
        String apiName = param.getApiName();
        String methodName = getMethodName(apiName);
        String msg = "接口不存在";
        String result;
        if(verifyDataAndIsBlank(param.getData())){
            Method method = isNull(ReflectUtil.getMethod(this.getClass(), methodName), msg);
            result = ReflectUtil.invoke(this, method);
        }else {
            Method method = isNull(ReflectUtil.getMethod(this.getClass(), methodName, String.class), msg);
            result = ReflectUtil.invoke(this, method, param.getData());
        }

        return BizResult.success("成功", result);
    }

    /**
     * 验证data是否合法 并判断是否为空 null和{}都是空
     * @param data
     * @return
     */
    private boolean verifyDataAndIsBlank(String data){
        if(StrUtil.isBlank(data)){
            return true;
        }
        if(StrUtil.startWith(data, "{") && StrUtil.endWith(data, "}")){
            if(data.length() == 2){
                return true;
            }
            verifyDataFormat(data);
            return false;
        }
        //不为空 并且不是以 { 开头 } 结尾 为不合法
        throw new BizException("参数格式错误");
    }

    /**
     * 验证是否能转成json
     * @param data
     */
    private void verifyDataFormat(String data) {
        try {
            JSONUtil.parseObj(data);
        } catch (Exception e) {
            throw new BizException("参数格式错误");
        }
    }

    private String getMethodName(String apiName) {
        return apiName.split("\\.")[3];
    }

    private <T> T isNull(T t, String msg){
        return Optional.ofNullable(t).orElseThrow(() -> new BizException(msg));
    }

    @OpenApi
    private void xxxx(String abc) {
        System.out.println(abc);
        System.out.println(111);
    }

    @OpenApi
    private void xxxx() {
        System.out.println("无参数 4X");
    }

    @OpenApi
    private String xxx(String abc) {
        System.out.println(abc);
        System.out.println(111);
        return "111";
    }

    @OpenApi
    private String xxx() {
        System.out.println(222);
        return "222";
    }


    public static void main(String[] args) {
        ItemNoServiceImpl itemNoService = new ItemNoServiceImpl();
        /*BizResult process = itemNoService.process(new ApiParam().setApiName("aa.a.a.xxxx").setData("{aa:1,bb:1}"));
        System.out.println(process);*/
        itemNoService.dsa();

    }


    private void asd() {

    }

    private void dsa() {
        StackTraceElement s = ThreadUtil.getStackTraceElement(4);
        System.out.println(s.getClassName() + "--" + s.getMethodName() + "-" + s.getLineNumber());
        dsd();
    }

    private void dsd() {

        asd();
    }
}
