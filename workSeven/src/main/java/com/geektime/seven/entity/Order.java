package com.geektime.seven.entity;

/**
 * 订单表
 */
public class Order {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 最后更新时间
     */
    private Long lastUpdateTime;

    /**
     * 订单状态：0创建，1已支付，2申请退款，3已退款
     */
    private Integer status;
}
