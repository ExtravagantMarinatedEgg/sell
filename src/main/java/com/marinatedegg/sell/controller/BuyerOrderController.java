package com.marinatedegg.sell.controller;

import com.marinatedegg.sell.VO.ServerResponse;
import com.marinatedegg.sell.converter.OrderForm2OrderDTOConverter;
import com.marinatedegg.sell.dto.OrderDTO;
import com.marinatedegg.sell.enums.ResultEnum;
import com.marinatedegg.sell.exception.SellException;
import com.marinatedegg.sell.form.OrderForm;
import com.marinatedegg.sell.service.BuyerService;
import com.marinatedegg.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家订单
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ServerResponse<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        //todo 看不懂 看那两门课 心机婊
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确， orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ServerResponse.createBySuccess(map);

    }
    //订单列表
    @GetMapping("/list")
    public ServerResponse<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOpage = orderService.findList(openid, pageRequest);
        return ServerResponse.createBySuccess(orderDTOpage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ServerResponse<OrderDTO> detail(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ServerResponse.createBySuccess(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ServerResponse cancel(@RequestParam("openid") String openid,
                                 @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ServerResponse.createBySuccess();
    }
}
