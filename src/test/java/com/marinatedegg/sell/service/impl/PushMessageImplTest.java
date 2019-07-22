package com.marinatedegg.sell.service.impl;

import com.marinatedegg.sell.dto.OrderDTO;
import com.marinatedegg.sell.service.OrderService;
import com.marinatedegg.sell.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageImplTest {

    @Autowired
    private PushMessageService pushMessage;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {

        OrderDTO orderDTO = orderService.findOne("1563175887032445833");

        pushMessage.orderStatus(orderDTO);
    }
}