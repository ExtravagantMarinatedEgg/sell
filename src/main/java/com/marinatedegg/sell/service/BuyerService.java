package com.marinatedegg.sell.service;

import com.marinatedegg.sell.dto.OrderDTO;

public interface BuyerService {

    //查询订单
    OrderDTO findOrderOne(String openid, String orderID);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
