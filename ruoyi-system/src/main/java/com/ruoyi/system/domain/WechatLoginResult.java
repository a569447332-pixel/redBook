// com/ruoyi/system/domain/WechatLoginResult.java
package com.ruoyi.system.domain;

import lombok.Data;

/**
 * 微信小程序登录返回结果
 */
@Data
public class WechatLoginResult {
    /** 错误码（0为成功） */
    private Integer errcode;
    /** 错误信息 */
    private String errmsg;
    /** 用户唯一标识 */
    private String openid;
    /** 会话密钥 */
    private String session_key;
    /** 用户在开放平台的唯一标识（绑定开放平台后才有） */
    private String unionid;
}