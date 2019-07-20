package com.marinatedegg.sell.form;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryForm {

    /**类目ID*/
    private Integer categoryId;
    /**类目名字*/
    private String categoryName;
    /**类目编号*/
    private Integer categoryType;
}
