package com.marinatedegg.sell.dao;

import com.marinatedegg.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao dao;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = dao.findById(1).orElse(null);
        System.out.println(productCategory.toString());
    }

    //产生异常回滚 test时直接回滚 @Transactional
    @Test
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("老年人", 7);
        ProductCategory result = dao.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void saveTestUpdate() {
//        ProductCategory productCategory = new ProductCategory();
//        productCategory.setCategoryId(3);
//        productCategory.setCategoryName("甜品");
//        productCategory.setCategoryType(4);
//        dao.save(productCategory);
        ProductCategory productCategory = dao.findById(3).orElse(null);
        productCategory.setCategoryType(11);
        dao.save(productCategory);

    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> result = dao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}