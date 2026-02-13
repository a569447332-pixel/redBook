package com.ruoyi.common.core.domain.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class AppLoginUser implements UserDetails {
    private Long userId;
    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() { return null; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    // getter & setter
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setUsername(String username) { this.username = username; }


    /**
     * 登录生成的 Token
     */
    private String token;

    /**
     * 登录时间（毫秒）
     */
    private long loginTime;

    /**
     * 过期时间（毫秒）
     */
    private long expireTime;

    // ==================== 补充缺失的 setter 方法 ====================

    public void setToken(String token) {
        this.token = token;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    // 可选：补充 getter 方法（如果其他地方需要读取）
    public String getToken() {
        return token;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

}