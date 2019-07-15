package com.marinatedegg.sell.dataobject;

import com.marinatedegg.sell.enums.OrderStatusEnum;
import com.marinatedegg.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 */
@Entity
@Data
public class OrderMaster {

    /*订单id*/
    @Id
    private String orderId;

    /*买家姓名*/
    private String buyerName;

    /*买家电话*/
    private String buyerPhone;

    /*买家地址*/
    private String buyerAddress;

    /*买家微信openid*/
    private String buyerOpenid;

    /*订单总金额*/
    private BigDecimal orderAmount;

    /*订单状态, 默认为新下单 0*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /*支付状态, 默认未支付 0*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;
    @Column(name="update_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

//    不太好，即对应数据库字段，又为上层服务。穿件dto数据传输层对象，在各层传输使用
//    @Transient
//    private List<OrderDetail> orderDetailList;

}
