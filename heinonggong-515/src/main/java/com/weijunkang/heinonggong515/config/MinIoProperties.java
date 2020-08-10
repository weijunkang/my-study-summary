package com.weijunkang.heinonggong515.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/01/14 09:32
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinIoProperties {
    /**
     * minio 服务地址 http://ip:port
     */
    private String url;
    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String secretKey;
    /**
     * 桶名称
     */
    private String bucketName;
}
