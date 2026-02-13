package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.EmailCodeCacheUtil;
import com.ruoyi.common.utils.EmailUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.AppMember;
import com.ruoyi.system.service.IAppMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.domain.model.AppLoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/app/api")
public class AppLoginController {

    @Autowired
    private IAppMemberService appMemberService;

    @Autowired
    private EmailCodeCacheUtil emailCodeCacheUtil;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailUtil emailUtil;


    /**
     * 发送邮箱验证码
     */
    @PostMapping("/sendCode")
    public AjaxResult sendCode(@RequestBody AppMember member) {
        String email = member.getEmail();
        if (StringUtils.isBlank(email)) {
            return AjaxResult.error("邮箱不能为空");
        }

        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        try {
            emailUtil.sendVerifyCode(email, code);
            emailCodeCacheUtil.setCode(email, code);
            return AjaxResult.success("验证码发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("验证码发送失败：" + e.getMessage());
        }
    }

    /**
     * 邮箱验证码登录（分离客户端会员）
     * 逻辑：邮箱存在则登录，不存在则自动注册（可根据需求关闭）
     */
    @PostMapping("/loginByEmail")
    public AjaxResult loginByEmail(@RequestBody AppMember member) {
        String email = member.getEmail();
        String code = member.getCode(); // 用remark临时接收验证码

        // 1. 校验参数
        if (StringUtils.isBlank(email) || StringUtils.isBlank(code)) {
            return AjaxResult.error("邮箱或验证码不能为空");
        }

        // 2. 校验验证码
        String cacheCode = emailCodeCacheUtil.getCode(email);
        if (!code.equals(cacheCode)) {
            return AjaxResult.error("验证码错误或已过期");
        }

        // 3. 查询客户端会员
        AppMember existMember = appMemberService.selectMemberByEmail(email);
        if (existMember == null) {
            // 可选：自动注册新会员
            existMember = new AppMember();
            existMember.setEmail(email);
            existMember.setNickName("用户" + email.substring(0, email.indexOf("@")));
            existMember.setStatus("0");
            existMember.setCreateTime(new Date());
            appMemberService.insertMember(existMember);
        } else if ("1".equals(existMember.getStatus())) {
            return AjaxResult.error("账号已被停用");
        }

        // 4. 生成30天有效期Token（用AppMember构建LoginUser）
        AppLoginUser appLoginUser = new AppLoginUser();
        appLoginUser.setUserId(existMember.getMemberId());
        appLoginUser.setUsername(existMember.getEmail());
        String token = tokenService.createAppToken(appLoginUser);

        // 存入 Security 上下文
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                appLoginUser,
                null,
                appLoginUser.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 5. 删除验证码
//        emailCodeCacheUtil.deleteCode(email);



        return AjaxResult.success("登录成功", token);
    }
}