package com.example.workeight.mapper;

import com.example.workeight.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Administrator
 */
public interface OrderMapper {

    /**
     * 获取所有订单
     * @return
     */
    @Select("SELECT * FROM t_order")
    @Results({
            @Result(property = "orderId",  column = "order_id"),
            @Result(property = "userId",  column = "user_id"),
            @Result(property = "productId",  column = "product_id"),
            @Result(property = "createTime",  column = "create_time"),
            @Result(property = "lastUpdateTime", column = "last_update_time")
    })
    List<Order> getAllOrders();

    /**
     * 根据订单id获取订单
     * @param orderId
     * @return
     */
    @Select("SELECT * FROM t_order where order_id = #{orderId}")
    @Results({
            @Result(property = "orderId",  column = "order_id"),
            @Result(property = "userId",  column = "user_id"),
            @Result(property = "productId",  column = "product_id"),
            @Result(property = "createTime",  column = "create_time"),
            @Result(property = "lastUpdateTime", column = "last_update_time")
    })
    Order getOrderById(Integer orderId);

    /**
     * 订单保存
     * @param order
     */
    @Insert("INSERT INTO t_order(`order_id`, `user_id`, `product_id`, `create_time`, `last_update_time`, `status`) VALUES(#{orderId}, #{userId}, #{productId}, #{createTime}, #{lastUpdateTime}, #{status})")
    void save(Order order);

    /**
     * 根据订单id修改订单状态
     * @param order
     */
    @Update("update t_order set status = #{status} where order_id = #{orderId}")
    void updateByOrderId(Order order);

    /**
     * 根据订单id删除订单
     * @param orderId
     */
    @Delete("delete t_order where order_id = #{orderId}")
    void delete(Integer orderId);

    /**
     * 获取订单总数
     * @return
     */
    @Select("select count(*) from t_order")
    int count();
}
