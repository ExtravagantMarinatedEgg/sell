package com.marinatedegg.sell.controller;

import com.marinatedegg.sell.dataobject.ProductCategory;
import com.marinatedegg.sell.form.CategoryForm;
import com.marinatedegg.sell.form.ProductForm;
import com.marinatedegg.sell.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 类目list
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory category = categoryService.findOne(categoryId);
            if (category == null) {
                map.put("msg", "类目不存在");
                map.put("url", "/sell/seller/category/list");
                return new ModelAndView("common/error", map);
            }
            map.put("category", category);
        }
        return new ModelAndView("category/index", map);
    }

    @PostMapping("save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        ProductCategory productCategory = new ProductCategory();

        try {
            if (categoryForm.getCategoryId() != null) {
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
                if (productCategory == null) {
                    map.put("msg", "类目不存在");
                    map.put("url", "/sell/seller/category/list");
                    return new ModelAndView("common/error", map);
                }
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);
        } catch (BeansException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }

}
