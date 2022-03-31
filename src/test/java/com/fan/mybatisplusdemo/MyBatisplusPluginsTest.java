package com.fan.mybatisplusdemo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fan.mybatisplusdemo.mapper.ProductMapper;
import com.fan.mybatisplusdemo.mapper.UserMapper;
import com.fan.mybatisplusdemo.pojo.Product;
import com.fan.mybatisplusdemo.pojo.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisplusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);
        System.out.println("test page");
        System.out.println(page.getRecords());
        System.out.println(page.getPages()); // 总页数
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
        System.out.println("test page");
    }

    @Test
    public void testPageVo() {
        // 测试自己配置的分页插件
        // select id, name, age, email from t_user where age > ? LIMIT ?
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPageVo(page, 20);
        System.out.println("test page");
        System.out.println(page.getRecords());
        System.out.println(page.getPages()); // 总页数
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
        System.out.println("test page");
    }

    @Test
    public void testProduct() {
        // 小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        // 小李查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格：" + productWang.getPrice());
        // 小李将商品价格加 50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        // 小王将商品价格减 30
        productWang.setPrice(productWang.getPrice() - 30);
        int result = productMapper.updateById(productWang);
        if (result == 0) {
            // 操作失败，重试
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
        }

        // 老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + productBoss.getPrice());
    }

}
