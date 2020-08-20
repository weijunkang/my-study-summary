package com.weijunkang.aopdome.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 日志
 * @date 2020/8/19 10:53
 */
@Data
@Accessors(chain = true)
@TableName(value = "code_log")
public class CodeLog {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * ip
     */
    private String ip;

    /**
     * url
     */
    private String url;

    /**
     * 参数
     */
    private String param;

    /**
     * 渠道代码
     */
    private String channelCode;

    /**
     *
     */
    private Integer status;

}
