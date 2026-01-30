package com.ruoyi.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMVC 配置（跨域、静态资源）
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域配置（前后端分离必配）
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                // 所有接口
                .allowedOriginPatterns("*")      // 允许所有域名（生产环境指定具体域名）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的请求方法
                .allowedHeaders("*")              // 允许的请求头
                .allowCredentials(true)           // 允许携带cookie
                .maxAge(3600);                    // 预检请求有效期（秒）
    }

    /**
     * 静态资源放行（如swagger、前端静态文件）
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Swagger UI 资源放行（SpringDoc/Swagger3）
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/");
        // 本地静态资源放行（如/static目录）
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}