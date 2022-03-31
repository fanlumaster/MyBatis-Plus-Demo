package com.fan.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fan.mybatisplusdemo.mapper.UserMapper;
import com.fan.mybatisplusdemo.pojo.User;
import com.fan.mybatisplusdemo.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
