package com.example.workeight.service;

import com.example.workeight.entity.Order;

import java.util.List;

/**
 * @author Administrator
 */
public interface OrderService {

    /**
     * 获取所有订单信息
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    Order getOrderById(Integer orderId);

    /**
     * 保存订单信息
     * @param order
     */
    void save(Order order);

    /**
     * 根据订单id修改订单状态
     * @param order
     */
    void updateByOrderId(Order order);

    /**
     * 根据订单id，删除订单
     * @param orderId
     */
    void delete(Integer orderId);
}
