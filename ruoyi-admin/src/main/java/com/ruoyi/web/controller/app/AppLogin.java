package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysNewsCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.config.WechatMiniAppConfig;
import com.ruoyi.system.domain.WechatLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


@RestController
@RequestMapping("/app/api") // 客户端接口统一前缀 /app
public class AppLogin {

    @Autowired
    private WechatMiniAppConfig wechatMiniAppConfig;

    @Autowired
    private RestTemplate restTemplate; // 若依默认已配置，无则需手动注入

    /**
     * 根据小程序code获取unionid/openid
     */
    @PostMapping("/login/wechat")
    public AjaxResult getUnionId(@RequestBody Map<String, String> params) {
        String code = params.get("code");
        if (code == null || code.isEmpty()) {
            return AjaxResult.error("code不能为空");
        }

        // 1. 拼接微信接口地址
        String url = String.format(
                "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                wechatMiniAppConfig.getAppid(),
                wechatMiniAppConfig.getSecret(),
                code
        );

        try {
            // 2. 调用微信接口
            String result = restTemplate.getForObject(url, String.class);
            WechatLoginResult loginResult = JSON.parseObject(result, WechatLoginResult.class);

            // 3. 处理返回结果
            if (loginResult.getErrcode() != null && loginResult.getErrcode() != 0) {
                System.out.println("接受到数据：" + params);
                return AjaxResult.error("获取unionid失败：" + loginResult.getErrmsg());
            }

            // 4. 返回unionid/openid（可根据业务存储到数据库）
            Map<String, String> data = new HashMap<>();
            data.put("unionid", loginResult.getUnionid());
            data.put("openid", loginResult.getOpenid());
            data.put("session_key", loginResult.getSession_key()); // 注意：session_key需加密存储，不要返回给前端
            return AjaxResult.success("获取成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("调用微信接口异常：" + e.getMessage());
        }
    }

}
