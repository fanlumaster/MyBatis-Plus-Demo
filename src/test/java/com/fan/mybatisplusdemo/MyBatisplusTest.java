package com.fan.mybatisplusdemo;

import java.util.Arrays;
import java.util.List;

import com.fan.mybatisplusdemo.mapper.UserMapper;
import com.fan.mybatisplusdemo.pojo.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisplusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        // 实现新增用户信息
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setName("陆羽");
        user.setAge(22);
        user.setEmail("luyu.@example.com");
        int result = userMapper.insert(user);
        System.out.println("result: " + result);
        System.out.println("id: " + user.getId());
    }

    @Test
    public void testDelete() {
        // 删除方法 1
        // DELETE FROM user WHERE id=?
        // int result = userMapper.deleteById(1509058498291027970L);
        // System.out.println("result: " + result);

        // 删除方法 2
        // DELETE FROM user WHERE name = ? AND age = ?
        // Map<String, Object> map = new HashMap<>();
        // map.put("name", "陆羽");
        // map.put("age", 22);
        // int result = userMapper.deleteByMap(map);
        // System.out.println("result: " + result);

        // 通过多个 id 来进行删除
        // DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println("result: " + result);
    }

    @Test
    public void testUpdate() {
        // 修改用户信息
        User user = new User();
        user.setId(4L);
        user.setName("Fany");
        user.setEmail("fany@email.com");
        int result = userMapper.updateById(user);
        System.out.println("result: " + result);
    }

    @Test
    public void testSelect() {
        // 通过 id 查询用户信息
        // User user = userMapper.selectById(1L);
        // System.out.println("user: " + user);

        // 根据多个 id 查询多个用户信息
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        // List<Long> list = Arrays.asList(1L, 2L, 3L);
        // List<User> users = userMapper.selectBatchIds(list);
        // users.forEach(System.out::println);

        // SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        // Map<String, Object> map = new HashMap<>();
        // map.put("name", "Jack");
        // map.put("age", 20);
        // List<User> users = userMapper.selectByMap(map);
        // users.forEach(System.out::println);

        // 查询所有数据
        // SELECT id,name,age,email FROM user
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

        // Map<String, Object> map = userMapper.selectMapById(1L);
        // System.out.println("map: " + map);
    }

}
