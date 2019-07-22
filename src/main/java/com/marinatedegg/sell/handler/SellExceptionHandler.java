package com.marinatedegg.sell.handler;

import com.marinatedegg.sell.config.ProjectUrlConfig;
import com.marinatedegg.sell.exception.SellAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig urlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException() {
        // todo 跳转到登录界面把就
        return new ModelAndView("redirect:"
                .concat(urlConfig.getSell())// todo urlConfig.getWechatOpenAuthorize()
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(urlConfig.getSell())
                .concat("/sell/seller/login"));
    }

}
