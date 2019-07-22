package com.marinatedegg.sell.handler;

import com.marinatedegg.sell.VO.ServerResponse;
import com.marinatedegg.sell.config.ProjectUrlConfig;
import com.marinatedegg.sell.exception.ResponseBankException;
import com.marinatedegg.sell.exception.SellAuthorizeException;
import com.marinatedegg.sell.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig urlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerSellerAuthorizeException() {
        // todo 跳转到登录界面把就
        return new ModelAndView("redirect:"
                .concat(urlConfig.getSell())// todo urlConfig.getWechatOpenAuthorize()
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(urlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ServerResponse handlerSellException(SellException e) {
        return ServerResponse.createByErrorCodeMessage(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException() {

    }
}
