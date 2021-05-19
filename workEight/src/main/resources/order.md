```
CREATE TABLE `t_order` (  
  `order_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` INT(11) NOT NULL COMMENT '用户id',
  `product_id` INT(11) NOT NULL COMMENT '商品id',
  `create_time` BIGINT(20) NOT NULL COMMENT '创建时间',
  `last_update_time` BIGINT(20) NOT NULL COMMENT '最后更新时间',
  `status` INT(1) NOT NULL COMMENT '订单状态：0创建，1已支付，2申请退款，3已退款',
  PRIMARY KEY (`order_id`) ,
  KEY `index_user_id` (`user_id`),
  KEY `index_product_id` (`product_id`)`sharding_db``sharding_db``sharding_db`
) 
AUTO_INCREMENT=1 COMMENT='订单表';
```