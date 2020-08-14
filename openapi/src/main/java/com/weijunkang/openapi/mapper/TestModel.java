package com.weijunkang.openapi.mapper;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/6/10 17:17
 */
@Data
@Accessors(chain = true)
@TableName(value = "sys_user")
public class TestModel{

    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

}
