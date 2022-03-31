package com.fan.mybatisplusdemo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fan.mybatisplusdemo.enums.SexEnum;

import lombok.Data;

@Data
// 设置对应的数据库中的表名
// @TableName("t_user")
public class User {

    // 将属性对应的字段指定为主键
    // value 属性用于指定主键的字段
    @TableId(value = "id")
    private Long id;
    // 指定属性对应的字段名
    @TableField(value = "name")
    private String name;
    private Integer age;
    private String email;
    private SexEnum sex;
    @TableLogic
    private Integer isDeleted;

}