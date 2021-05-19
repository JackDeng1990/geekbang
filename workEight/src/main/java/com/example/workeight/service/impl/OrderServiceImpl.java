package com.example.workeight.service.impl;

import com.example.workeight.entity.Order;
import com.example.workeight.mapper.OrderMapper;
import com.example.workeight.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;


    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public Order getOrderById(Integer orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Override
    public void save(Order order) {
        int count = orderMapper.count();
        order.setOrderId(count + 1);
        orderMapper.save(order);
    }

    @Override
    public void updateByOrderId(Order order) {
        orderMapper.updateByOrderId(order);
    }

    @Override
    public void delete(Integer orderId) {
        orderMapper.delete(orderId);
    }
}
