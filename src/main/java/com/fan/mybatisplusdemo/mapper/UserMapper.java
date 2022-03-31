package com.fan.mybatisplusdemo.mapper;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fan.mybatisplusdemo.pojo.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据 id 查询用户信息为 map 集合
     * 
     * @param id
     * @return
     */
    Map<String, Object> selectMapById(Long id);

    /**
     * 通过年龄查询用户信息并分页
     * 
     * @param page MyBatis Plus 所提供的分页对象，必须位于第一个参数的位置
     * @param age
     * @return
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, Integer age);

}
