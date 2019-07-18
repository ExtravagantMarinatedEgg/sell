package com.marinatedegg.sell.controller;

import com.marinatedegg.sell.dataobject.ProductInfo;
import com.marinatedegg.sell.enums.ResultEnum;
import com.marinatedegg.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage =  productInfoService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @GetMapping("off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = productInfoService.offSale(productId);
        } catch (Exception e) {
            log.error("【商品下架】 发生异常 {}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
//        ProductInfoPage productInfoPage = new P
//        map.put("productInfoPage", productInfoPage);
        map.put("msg", ResultEnum.PRODUCT_OFF_SALE_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success");
    }

    /**
     * 上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = productInfoService.onSale(productId);
        } catch (Exception e) {
            log.error("【商品上架】 发生异常 {}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
//        ProductInfoPage productInfoPage = new P
//        map.put("productInfoPage", productInfoPage);
        map.put("msg", ResultEnum.PRODUCT_ON_SALE_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success");
    }

    /**
     * 详情？？ 商品修改那？
     * @param productId
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam("productId") String productId) {
        try {
            productInfoService.findOne(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }


}
