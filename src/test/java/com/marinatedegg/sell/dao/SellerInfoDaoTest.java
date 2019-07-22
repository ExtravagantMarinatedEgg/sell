package com.marinatedegg.sell.dao;

import com.marinatedegg.sell.config.PasswordSaltConfig;
import com.marinatedegg.sell.dataobject.SellerInfo;
import com.marinatedegg.sell.utils.KeyUtil;
import com.marinatedegg.sell.utils.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao dao;

    @Autowired
    private PasswordSaltConfig passwordSaltConfig;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("ludan");
        sellerInfo.setPassword(MD5Util.MD5EncodeUtf8("123456"));
        sellerInfo.setOpenid("123456");
        SellerInfo result = dao.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid() {
        SellerInfo result = dao.findByOpenid("123456");
        Assert.assertEquals("ludan", result.getUsername());
    }

}