package com.weijunkang.openapi.config;

import com.weijunkang.openapi.advice.InitVerifyData;
import com.weijunkang.openapi.advice.VerifyRequestBodyAdvice;
import com.weijunkang.openapi.service.InitVerifyDataService;
import com.weijunkang.openapi.service.UpdateVerifyDataService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.Collections;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 安全验证配置
 * @date 2020/8/13 13:51
 */
@Data
@ConfigurationProperties(prefix = "verify")
public class SecurityVerifyConfig {

    /**
     * 开启验证
     */
    private boolean open = true;

    /**
     * 是否显示日志
     */
    private boolean showLog = false;

    /**
     * 请求数据时间戳校验时间差
     * 超过指定时间的数据认定为伪造
     */
    private boolean timestampCheck = true;

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Autowired
    private InitVerifyDataService initVerifyDataService;

    @Autowired
    private UpdateVerifyDataService updateVerifyDataService;

    @PostConstruct
    public void init() {
        InitVerifyData initVerifyData = new InitVerifyData(initVerifyDataService);
        // 把VerifyRequestBodyAdvice加入到 requestMappingHandlerAdapter中 并刷新
        // 效果与在VerifyRequestBodyAdvice上加@ControllerAdvice相同
        requestMappingHandlerAdapter.setRequestBodyAdvice(Collections.singletonList(new VerifyRequestBodyAdvice(this, initVerifyData)));
        requestMappingHandlerAdapter.setArgumentResolvers(null);
        requestMappingHandlerAdapter.afterPropertiesSet();

        /**
         * 把initVerifyData传入UpdateVerifyDataService
         * 用于更新initVerifyData中的数据 主要更新接入方信息
         */
        updateVerifyDataService.setVerifyData(initVerifyData);
    }



}
