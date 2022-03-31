package com.fan.mybatisplusdemo;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fan.mybatisplusdemo.mapper.UserMapper;
import com.fan.mybatisplusdemo.pojo.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisplusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        // 查询用户名包含 a，年龄在 20 到 30 之间，邮箱信息不为 null 的用户信息
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (name
        // LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        // 查询用户信息，按照年龄的降序排序，若年龄相同，则按 id 的升序排序
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY
        // age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test03() {
        // 删除邮箱信息为 null 的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result: " + result);
    }

    @Test
    public void test04() {
        // 将（年龄大于 20 并且用户名中包含有 a）或邮箱为 null 的用户信息修改
        // UPDATE t_user SET name=?, email=? WHERE is_deleted=0 AND (age > ? AND name
        // LIKE ? OR email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .like("name", "a")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("Ming");
        user.setEmail("shell@email.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result: " + result);
    }

    @Test
    public void test05() {
        // 将用户名中包含有 a 并且（年龄大于 20 或邮箱为 null）的用户信息修改
        // lambda 中的条件优先执行
        // UPDATE t_user SET name=?, email=? WHERE is_deleted=0 AND (name LIKE ? AND
        // (age > ? OR email IS NULL))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setName("Hong");
        user.setEmail("hong@email.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result: " + result);
    }

    @Test
    public void test06() {
        // 查询用户的姓名、年龄、邮箱信息
        // SELECT name,age,email FROM t_user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07() {
        // 查询 id 小于等于 100 的信息
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (id IN
        // (select id from t_user where id <= 100))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from t_user where id <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test08() {
        // 将用户名中包含有 a 并且（年龄大于 20 或邮箱为 null）的用户信息修改
        // UPDATE t_user SET name=?,email=? WHERE is_deleted=0 AND (name LIKE ? AND (age
        // > ? OR email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("name", "Black").set("email", "black@email.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result: " + result);
    }

    @Test
    public void test09() {
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            // isNotBlank 判断某个字符串是否不为空字符串，不为 null，不为空白符
            queryWrapper.like("name", name);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test10() {
        // 带有 condition 参数的方法测试
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (name
        // LIKE ? AND age <= ?)
        String name = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test11() {
        // lambada 条件构造，函数式获取属性，主要是为了防止属性名写错
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (name
        // LIKE ? AND age <= ?)
        String name = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), User::getName, name)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test12() {
        // 同样是 lambda 更新条件构造器
        // UPDATE t_user SET name=?,email=? WHERE is_deleted=0 AND (name LIKE ? AND (age
        // > ? OR email IS NULL))
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName, "Black").set(User::getEmail, "black@email.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result: " + result);
    }

}
