package com.marinatedegg.sell.aspect;

import com.marinatedegg.sell.constant.CookieConstant;
import com.marinatedegg.sell.constant.RedisConstant;
import com.marinatedegg.sell.exception.SellAuthorizeException;
import com.marinatedegg.sell.exception.SellException;
import com.marinatedegg.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

//    @Pointcut("execution(public * com.marinatedegg.sell.controller.Seller*.*(..))" +
//    "&& !execution(public * com.marinatedegg.sell.controller.SellerUserController.*(..))")
//    public void verify(){
//
//    }
//
//    @Before("verify()")
//    public void doVerify() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        //查询cookie
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if (cookie == null) {
//            log.warn("【登录校验】 Cookie中查询不到token");
//            throw new SellAuthorizeException();
//        }
//        //去redis里查询
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//        if (StringUtils.isEmpty(tokenValue)) {
//            log.warn("【登录校验】 Redis中查询不到token");
//            throw new SellAuthorizeException();
//        }
//    }

}
