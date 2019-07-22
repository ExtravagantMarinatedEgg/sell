package com.marinatedegg.sell.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Data
@Component
//@ConfigurationProperties(prefix = "password")
public class PasswordSaltConfig {
//    private String salt;
//
//    private static String salt1;
//
//    @PostConstruct
//    private void setSalt1() {
//        salt1 = this.salt;
//    }
//
//    public String getSalt() {
//        return this.salt1;
//    }


    @Autowired
    private Environment env;

    private static String salt;

    @PostConstruct
    private void readConfig() {
        salt = env.getProperty("password.salt");
    }
    public static String getSalt() {
        return salt;
    }
}
