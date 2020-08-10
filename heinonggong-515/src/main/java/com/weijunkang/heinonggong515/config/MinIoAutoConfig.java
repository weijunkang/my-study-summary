package com.weijunkang.heinonggong515.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2019/12/30 16:18
 */
@Configuration
public class MinIoAutoConfig {

    @Autowired
    private MinIoProperties properties;

    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(properties.getUrl(), properties.getAccessKey(), properties.getSecretKey());
    }
}
