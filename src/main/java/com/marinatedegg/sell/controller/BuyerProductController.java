package com.marinatedegg.sell.controller;

import com.marinatedegg.sell.VO.ProductInfoVO;
import com.marinatedegg.sell.VO.ProductVO;
import com.marinatedegg.sell.VO.ServerResponse;
import com.marinatedegg.sell.dataobject.ProductCategory;
import com.marinatedegg.sell.dataobject.ProductInfo;
import com.marinatedegg.sell.service.ProductCategoryService;
import com.marinatedegg.sell.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
//@CacheConfig(cacheNames = "product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123", unless = "#result.getStatus() != 0")
    public ServerResponse list() {

        //查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();


        //查询类目（一次查询）
            //传统做法
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for (ProductInfo item : productInfoList) {
//            categoryTypeList.add(item.getCategoryType());
//        }
            //精简做法
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //数据拼装

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory item : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(item.getCategoryName());
            productVO.setCategoryType(item.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfoitem : productInfoList) {
                if (productInfoitem.getCategoryType().equals(item.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfoitem, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ServerResponse.createBySuccess(productVOList);
    }
}
