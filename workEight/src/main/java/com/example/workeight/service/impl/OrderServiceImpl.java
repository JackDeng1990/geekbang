package com.example.workeight.service.impl;

import com.example.workeight.entity.Order;
import com.example.workeight.mapper.OrderMapper;
import com.example.workeight.service.OrderService;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import org.apache.shardingsphere.transaction.xa.manager.atomikos.AtomikosTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransactional() {
        Order order1 = new Order();
        order1.setUserId((int)(Math.random() * 100));
        order1.setCreateTime(System.currentTimeMillis());
        order1.setLastUpdateTime(System.currentTimeMillis());
        order1.setProductId(1);
        order1.setStatus(1);
        this.save(order1);
        Order order2 = new Order();
        order2.setUserId((int)(Math.random() * 100));
        order2.setCreateTime(System.currentTimeMillis());
        order2.setLastUpdateTime(System.currentTimeMillis());
        order2.setProductId(1);
        order2.setStatus(1);
        this.save(order2);
        System.out.println(100 / 0);
    }
}
