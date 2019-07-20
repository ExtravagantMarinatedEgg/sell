package com.marinatedegg.sell.controller;

import com.marinatedegg.sell.VO.ProductIndexVO;
import com.marinatedegg.sell.dataobject.ProductCategory;
import com.marinatedegg.sell.dataobject.ProductInfo;
import com.marinatedegg.sell.enums.ProductStatusEnum;
import com.marinatedegg.sell.enums.ResultEnum;
import com.marinatedegg.sell.form.OrderForm;
import com.marinatedegg.sell.form.ProductForm;
import com.marinatedegg.sell.service.ProductCategoryService;
import com.marinatedegg.sell.service.ProductInfoService;
import com.marinatedegg.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Component
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService categoryService;

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
        return new ModelAndView("common/success", map);
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
        return new ModelAndView("common/success", map);
    }

    /**
     * 详情 新增修改
     * @param productId
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            ProductIndexVO productIndexVO = new ProductIndexVO();
            BeanUtils.copyProperties(productInfo, productIndexVO);
            productIndexVO.setProductStock(productInfo.getProductStock().toString());
            map.put("productInfo", productIndexVO);
        }

        //查询所有类目
        List<ProductCategory> categoryList =  categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }

    /**
     * 新增 修改商品
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空 说明新增
            if (!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = productInfoService.findOne(productForm.getProductId());
                BeanUtils.copyProperties(productForm, productInfo);
            } else {
                BeanUtils.copyProperties(productForm, productInfo);
                productInfo.setProductId(KeyUtil.genUniqueKey());
                //可在pojo对象中添加默认值
                productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            }


            productInfoService.save(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }



}
