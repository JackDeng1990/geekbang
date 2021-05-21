package com.example.workeight.controller;

import com.example.workeight.entity.Order;
import com.example.workeight.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @RequestMapping("/save")
    public Order save(){
        Order order = new Order();
        order.setUserId((int)(Math.random() * 100));
        order.setCreateTime(System.currentTimeMillis());
        order.setLastUpdateTime(System.currentTimeMillis());
        order.setProductId(1);
        order.setStatus(1);
        orderService.save(order);
        return order;
    }

    @RequestMapping("/update")
    public Order update(){
        Order order = new Order();
        order.setOrderId((int)(Math.random() * 100));
        order.setStatus(2);
        orderService.updateByOrderId(order);
        return order;
    }

    @RequestMapping("/getById")
    public Order getById(){
        Integer orderId = (int)(Math.random() * 100);
        Order order = orderService.getOrderById(orderId);
        return order;
    }

    @RequestMapping("/delete")
    public int delete(){
        Integer orderId = (int)(Math.random() * 100);
        orderService.delete(orderId);
        return 1;
    }

    @RequestMapping("/testTransactional")
    public int testTransactional(){
        orderService.testTransactional();
        return 1;
    }
}
