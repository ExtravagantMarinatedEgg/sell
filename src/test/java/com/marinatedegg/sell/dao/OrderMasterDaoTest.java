package com.marinatedegg.sell.dao;

import com.marinatedegg.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao dao;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("卤蛋");
        orderMaster.setBuyerPhone("13333333333");
        orderMaster.setBuyerAddress("大连民族大学");
        orderMaster.setBuyerOpenid("000000");
        orderMaster.setOrderAmount(new BigDecimal(200.55));
        OrderMaster result = dao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderMaster> result = dao.findByBuyerOpenid("000000", pageRequest);
        Assert.assertNotEquals(0, result.getTotalElements());
    }

}