package com.marinatedegg.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {


    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code, @RequestParam("state") String state) {
//        https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc88f38cab217c2bb&redirect_uri=http%3a%2f%2fludan.natapp1.cc%2fsell%2fweixin%2fauth&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
//        https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxc88f38cab217c2bb&secret=1eeccff772b2df152ddae9fb416087e2&code=CODE&grant_type=authorization_code

        log.info("进入auth方法 code={}, state={}", code, state);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxc88f38cab217c2bb&secret=1eeccff772b2df152ddae9fb416087e2&code=" + code + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);

    }


}
