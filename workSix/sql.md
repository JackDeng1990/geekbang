```
CREATE TABLE `geektime`.`user` ( 
`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id', 
`password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '密码', 
`name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '姓名', 
`telphone` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '电话', 
`birthday` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '出生日期', 
`email` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '邮箱', 
`create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间', 
`last_update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '最后修改时间', 
PRIMARY KEY (`id`) , 
KEY `index_telephone` (`telphone`) , 
KEY `index_name` (`name`) 
) ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COMMENT = '用户表'; 


CREATE TABLE `geektime`.`category` (  
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '分类名称',
  `create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `last_update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '最后更新时间',
  PRIMARY KEY (`id`) 
) ENGINE=INNODB CHARSET=utf8mb4
AUTO_INCREMENT=1 COMMENT='商品分类表';

CREATE TABLE `geektime`.`product` ( 
`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id', 
`name` VARCHAR(50) NOT NULL COMMENT '商品名称', 
`category_id` INT(11) NOT NULL COMMENT '商品分类id', 
`price` DOUBLE(10,2) NOT NULL DEFAULT 0.00 COMMENT '价格', 
`status` INT(1) NOT NULL COMMENT '商品状态：0下架，1上架', 
`stock` INT(11) NOT NULL COMMENT '库存', 
`create_time` BIGINT(20) NOT NULL COMMENT '创建时间', 
`last_update_time` BIGINT(20) NOT NULL COMMENT '最后更新时间', 
PRIMARY KEY (`id`) , 
KEY `index_category` (`category_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 AUTO_INCREMENT=1 COMMENT='商品表'; 

CREATE TABLE `geektime`.`order` (  
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` INT(11) NOT NULL COMMENT '用户id',
  `product_id` INT(11) NOT NULL COMMENT '商品id',
  `create_time` BIGINT(20) NOT NULL COMMENT '创建时间',
  `last_update_time` BIGINT(20) NOT NULL COMMENT '最后更新时间',
  `status` INT(1) NOT NULL COMMENT '订单状态：0创建，1已支付，2申请退款，3已退款',
  PRIMARY KEY (`id`) ,
  KEY `index_user_id` (`user_id`),
  KEY `index_product_id` (`product_id`)
) ENGINE=INNODB CHARSET=utf8mb4
AUTO_INCREMENT=1 COMMENT='订单表';


```

