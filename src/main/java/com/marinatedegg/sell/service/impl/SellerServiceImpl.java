package com.marinatedegg.sell.service.impl;

import com.marinatedegg.sell.dao.SellerInfoDao;
import com.marinatedegg.sell.dataobject.SellerInfo;
import com.marinatedegg.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
