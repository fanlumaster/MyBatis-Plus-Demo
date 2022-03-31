# 基本的数据库搭建
create database mybatis_plus;

use `mybatis_plus`;
create table `user` (
	`id` bigint(20) NOT NULL comment '主键 id',
    `name` varchar(30) default null comment '姓名',
    `age` int(11) default null comment '年龄',
    `email` varchar(50) default null comment '邮箱',
    primary key(`id`)
) engine=InnoDB default charset=utf8;

insert into t_user(id, name, age, email) values
(1, 'Jone', 18, 'test@baomidou.com'),
(2, 'Jack', 20, 'test@baomidou.com'),
(3, 'Tom', 28, 'test@baomidou.com'),
(4, 'Sandy', 21, 'test@baomidou.com'),
(5, 'Billie', 24, 'test@baomidou.com');

select * from t_user;

truncate table t_user;

create table t_product
(
	id bigint(20) not null comment '主键 id',
    name varchar(30) null default null comment '商品名称',
    price int(11) default 0 comment '价格',
    version int(11) default 0 comment '乐观锁版本号',
    primary key(id)
);

select * from t_product;

insert into t_product (id, name, price) values (1, 'Alianware Computer', 100);