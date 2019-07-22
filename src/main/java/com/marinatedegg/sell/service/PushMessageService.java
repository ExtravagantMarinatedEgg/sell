package com.marinatedegg.sell.service;

import com.marinatedegg.sell.dto.OrderDTO;

/**
 * 微信消息推送
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
