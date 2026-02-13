package com.ruoyi.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * 邮箱验证码缓存工具（有效期5分钟）
 */
@Component
public class EmailCodeCacheUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    // 缓存前缀
    private static final String CACHE_KEY_PREFIX = "email:code:";
    // 验证码有效期（5分钟）
    private static final long EXPIRE_MINUTES = 5;

    /**
     * 缓存验证码
     */
    public void setCode(String email, String code) {
        redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + email, code, EXPIRE_MINUTES, TimeUnit.MINUTES);
    }

    /**
     * 获取验证码
     */
    public String getCode(String email) {
        return redisTemplate.opsForValue().get(CACHE_KEY_PREFIX + email);
    }

    /**
     * 删除验证码（验证成功后）
     */
    public void deleteCode(String email) {
        redisTemplate.delete(CACHE_KEY_PREFIX + email);
    }
}