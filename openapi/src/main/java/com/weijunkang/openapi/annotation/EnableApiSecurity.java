package com.weijunkang.openapi.annotation;

import com.weijunkang.openapi.config.SecurityVerifyConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author weijunkang
 * @description: 启动接口安全验证
 * @date 2020/8/13 13:47
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({SecurityVerifyConfig.class})
public @interface EnableApiSecurity {
}
