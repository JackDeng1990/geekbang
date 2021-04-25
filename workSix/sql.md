```
CREATE TABLE `user` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
`password` varchar(75) NOT NULL COMMENT '密码',
`name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
`telphone` varchar(20) NOT NULL DEFAULT '' COMMENT '电话号',
`birthday` char(10) NOT NULL DEFAULT '' COMMENT '出生年月',
`email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
`create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
`last_update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后操作时间',
PRIMARY KEY (`id`),
KEY `index_telphone` (`telphone`),
KEY `index_email` (`email`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '用户表';


CREATE TABLE `category` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
`name` varchar(50) NOT NULL DEFAULT '' COMMENT '分类名称',
`create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
`last_update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后操作时间',
PRIMARY KEY (`id`),
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '商品分类表';



CREATE TABLE `category` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
`name` varchar(50) NOT NULL DEFAULT '' COMMENT '分类名称',
`create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
`last_update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后操作时间',
PRIMARY KEY (`id`),
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '商品分类表';



CREATE TABLE `product` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
`name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名称',
`category_id` int(11) NOT NULL  DEFAULT -1 COMMENT '分类id',
`Price` double(10,2) NOT NULL  DEFAULT 0.00 COMMENT '商品价格',
`status` int(11) NOT NULL  DEFAULT -1 COMMENT '商品状态：0下架，1上架',
`stock` int(11) NOT NULL  DEFAULT -1 COMMENT '商品库存',
`create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
`last_update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后操作时间',
PRIMARY KEY (`id`),
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '商品表';


CREATE TABLE `order` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
`user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '用户id',
`category_id` int(11) NOT NULL  DEFAULT -1 COMMENT '分类id',
`create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
`last_update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后操作时间',
PRIMARY KEY (`id`),
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '订单表';
```

