package com.marinatedegg.sell.service.impl;

import com.marinatedegg.sell.config.WechatAccountConfig;
import com.marinatedegg.sell.dto.OrderDTO;
import com.marinatedegg.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = new ArrayList<>();
        data.add(new WxMpTemplateData("first", "亲，测试来啦 完结订单"));
        data.add(new WxMpTemplateData("keyword1", "微信点餐"));
        data.add(new WxMpTemplateData("keyword2", "13333333333"));
        data.add(new WxMpTemplateData("keyword3", orderDTO.getOrderId()));
        data.add(new WxMpTemplateData("keyword4", orderDTO.orderStatusEnum().getMsg()));
        data.add(new WxMpTemplateData("keyword5", "￥" + orderDTO.getOrderAmount()));
        data.add(new WxMpTemplateData("remark", "就酱吧"));
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】 发送失败，{}", e);
        }
    }
}
