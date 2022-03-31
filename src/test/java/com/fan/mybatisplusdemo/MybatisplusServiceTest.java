package com.fan.mybatisplusdemo;

import java.util.ArrayList;
import java.util.List;

import com.fan.mybatisplusdemo.pojo.User;
import com.fan.mybatisplusdemo.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisplusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetCount() {
        // 查询总记录数
        // SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testInsertMore() {
        // 批量添加
        // INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setName("fanyfull" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println("result: " + b);
    }

}
