// com/ruoyi/system/config/WechatMiniAppConfig.java
package com.ruoyi.system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "ruoyi.wechat")
public class WechatMiniAppConfig {
    /** 小程序appid */
    private String appid;
    /** 小程序secret */
    private String secret;
}