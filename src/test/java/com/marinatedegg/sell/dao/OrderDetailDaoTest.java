package com.marinatedegg.sell.dao;

import com.marinatedegg.sell.dataobject.OrderDetail;
import com.marinatedegg.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao dao;

    @Test
    public void saveTest() {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567");
        orderDetail.setOrderId("111111");
        orderDetail.setProductId("123456");
        orderDetail.setProductIcon("http://xxxxx.jpg");
        orderDetail.setProductName("卤蛋");
        orderDetail.setProductPrice(new BigDecimal(200));
        orderDetail.setProductQuantity(100);

        OrderDetail result = dao.save(orderDetail);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = dao.findByOrderId("111111");
        Assert.assertNotEquals(0, result.size());
    }

}