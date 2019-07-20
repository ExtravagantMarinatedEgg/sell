package com.marinatedegg.sell.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marinatedegg.sell.enums.ProductStatusEnum;
import com.marinatedegg.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductIndexVO {

    /*商品Id*/
    private String productId;

    /*商品名称*/
    private String productName;

    /*单价*/
    private BigDecimal productPrice;

    /*库存*/
    private String productStock;

    /*描述*/
    private String productDescription;

    /*小图*/
    private String productIcon;

    /*商品状态,0正常1下架*/
    private Integer productStatus;

    /*类目编号*/
    private Integer categoryType;

//    private Date createTime;
//
//    private Date updateTime;

//    @JsonIgnore
//    public ProductStatusEnum getProductStatusEnum() {
//        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
//    }
}
