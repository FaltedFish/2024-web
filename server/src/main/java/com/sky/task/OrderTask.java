package com.sky.task;

import com.sky.entity.Dish;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.mapper.DishMapper;
import com.sky.mapper.OrderDetailMapper;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时任务类，订单定时处理
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private DishMapper dishMapper;

    /**
     * 处理超时订单的方法
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeoutOrder(){
        log.info("订单超时：{}", LocalDateTime.now());
        List<Orders> orderList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, LocalDateTime.now().plusMinutes(-15));

        if(orderList!=null && !orderList.isEmpty()){
            for(Orders orders:orderList){
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时");
                orders.setOrderTime(LocalDateTime.now());
                orderMapper.update(orders);
                List<OrderDetail> orderDetailList = orderDetailMapper.getByOrderId(orders.getId());
                for(OrderDetail orderDetail:orderDetailList){
                    Dish dish = dishMapper.getById(orderDetail.getDishId());
                    dish.setInventory(dish.getInventory()+orderDetail.getNumber());
                    dishMapper.update(dish);
                }
            }
        }
    }
    /**
     * 处理一直处于制作中的订单
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDelivery(){
        List<Orders> orderList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, LocalDateTime.now().plusMinutes(-60));

        if(orderList!=null && !orderList.isEmpty()){
            for(Orders orders:orderList){
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            }
        }
    }
}
