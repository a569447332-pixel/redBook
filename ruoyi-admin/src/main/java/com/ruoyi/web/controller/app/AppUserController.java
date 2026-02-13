package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.AppMember;
import com.ruoyi.system.service.IAppMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.domain.model.AppLoginUser;

@RestController
@RequestMapping("/app/api")
public class AppUserController {

    @Autowired
    private IAppMemberService appMemberService;

    /**
     * 获取当前登录 App 用户的详情信息
     */
    @GetMapping("/user/detail")
    public AjaxResult getUserDetail() {
        // 直接从上下文取 Principal
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 1. 判断是否未登录
        if (principal instanceof String && "anonymousUser".equals(principal)) {
            return AjaxResult.error("请先登录后再查看用户信息");
        }

        // 2. 强制判断是否为 AppLoginUser
        if (!(principal instanceof AppLoginUser)) {
            return AjaxResult.error("当前登录类型异常，请使用 App 端账号登录");
        }

        try {
            AppLoginUser appLoginUser = (AppLoginUser) principal;
            Long userId = appLoginUser.getUserId();
            AppMember member = appMemberService.selectMemberById(userId);

            if (member == null) {
                return AjaxResult.error("用户信息不存在");
            }
            return AjaxResult.success("查询成功", member);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("查询用户信息失败：" + e.getMessage());
        }
    }
}