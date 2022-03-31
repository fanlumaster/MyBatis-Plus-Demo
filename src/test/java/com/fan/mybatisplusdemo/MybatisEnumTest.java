package com.fan.mybatisplusdemo;

import com.fan.mybatisplusdemo.enums.SexEnum;
import com.fan.mybatisplusdemo.mapper.UserMapper;
import com.fan.mybatisplusdemo.pojo.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println("result: " + result);
    }

}
