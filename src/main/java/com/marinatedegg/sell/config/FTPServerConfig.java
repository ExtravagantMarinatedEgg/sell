package com.marinatedegg.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ConfigurationProperties(prefix = "ftp")
@Component
public class FTPServerConfig {

    private String user;

    private String pass;

    private String ip;

    /**
     * ftp端口
     */
    private String port;

    /**
     * 远程路径
     */
    private String remotePath;

    /**
     * 图片服务器地址
     */
    private String httpPrefix;

}
