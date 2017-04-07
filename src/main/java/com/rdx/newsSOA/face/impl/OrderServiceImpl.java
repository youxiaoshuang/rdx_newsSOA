package com.rdx.newsSOA.face.impl;

import com.rdx.newsSOA.dao.OrderEntityMapper;
import com.rdx.newsSOA.entity.OrderEntity;
import com.rdx.newsSOA.face.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 利用同步锁生成订单
 * Created by youxiaoshuang on 16/4/10.
 */
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderEntityMapper orderEntityMapper;
    @Transactional
    public synchronized String createOrderNO() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        OrderEntity order = new OrderEntity();
        order.setOrderno("test");
        orderEntityMapper.insertSelective(order);
        int id = order.getId();
        String orderNo = sdf.format(new Date()) +id;
        order.setOrderno(orderNo);
        orderEntityMapper.updateByPrimaryKeySelective(order);
        System.out.println("线程"+Thread.currentThread().getName()+":生成的订单号为"+orderNo);
        return orderNo;
    }
}
