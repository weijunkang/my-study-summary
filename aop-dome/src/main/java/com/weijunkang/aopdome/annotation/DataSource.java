package com.weijunkang.aopdome.annotation;

import com.weijunkang.aopdome.domain.enums.DataSourceType;

/**
 * @author weijunkang
 * @description: 切换数据源
 * @date 2020/8/20 15:39
 */
public @interface DataSource {

    /**
     * 切换数据源名称
     */
    DataSourceType value() default DataSourceType.MASTER;
}
