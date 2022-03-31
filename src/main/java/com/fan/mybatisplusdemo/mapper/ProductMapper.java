package com.fan.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fan.mybatisplusdemo.pojo.Product;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper extends BaseMapper<Product> {

}
