package com.marinatedegg.sell.dao;

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
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao dao;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("至尊奢华的卤蛋");
        productInfo.setProductPrice(new BigDecimal(2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("至尊奢华");
        productInfo.setProductIcon("http://ludan.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(99);
        ProductInfo result = dao.save(productInfo);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = dao.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
    }




}