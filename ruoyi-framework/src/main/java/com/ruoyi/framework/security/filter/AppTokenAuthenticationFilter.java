package com.ruoyi.framework.security.filter;

import com.ruoyi.common.core.domain.model.AppLoginUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * App端Token认证拦截器（仅处理/app/**接口）
 */
@Component
public class AppTokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI();

        // 只处理 /app/** 开头的接口
        if (requestUri.startsWith("/app/")) {
            // 1. 清空原生Security上下文（避免后台LoginUser干扰）
            SecurityContextHolder.clearContext();

            // 2. 从请求头获取App Token（无Bearer前缀）
            String token = request.getHeader("Authorization");
            if (StringUtils.isNotBlank(token)) {
                // 3. 从Redis读取AppLoginUser
                AppLoginUser appLoginUser = tokenService.getAppLoginUserFromRedis(token);
                if (appLoginUser != null) {
                    // 4. 构建认证对象并绑定到上下文
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            appLoginUser, null, Collections.emptyList() // 空权限列表
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        // 继续执行过滤器链
        chain.doFilter(request, response);
    }
}