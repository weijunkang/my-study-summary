package com.weijunkang.openapi.advice;

import com.weijunkang.openapi.annotation.Verify;
import com.weijunkang.openapi.config.SecurityVerifyConfig;
import com.weijunkang.openapi.exception.VerifyRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 验证请求正文
 * @date 2020/8/13 13:55
 */
@Slf4j
public class VerifyRequestBodyAdvice implements RequestBodyAdvice {

    private boolean encrypt;

    private Verify verifyAnnotation;

    private SecurityVerifyConfig securityVerifyConfig;

    private InitVerifyData initVerifyData;

    public VerifyRequestBodyAdvice(SecurityVerifyConfig securityVerifyConfig, InitVerifyData initVerifyData) {
        this.securityVerifyConfig = securityVerifyConfig;
        this.initVerifyData = initVerifyData;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        if (Objects.isNull(method)) {
            encrypt = false;
            return false;
        }
        if (method.isAnnotationPresent(Verify.class) && securityVerifyConfig.isOpen()) {
            encrypt = true;
            verifyAnnotation = methodParameter.getMethodAnnotation(Verify.class);
            return true;
        }
        // 此处如果按照原逻辑直接返回encrypt, 会造成一次修改为true之后, 后续请求都会变成true, 在不支持时, 需要做修正
        encrypt = false;
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        if (encrypt) {
            try {
                return new VerifyHttpInputMessage(httpInputMessage, securityVerifyConfig, verifyAnnotation, initVerifyData);
            } catch (VerifyRequestException e) {
                throw e;
            } catch (Exception e) {
                log.error("验证失败", e);
            }
        }
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }
}
