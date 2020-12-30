package com.half.boot05webadmin.config;

import com.half.boot05webadmin.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/26 15:07
 * @Version 1.0
 * @Description
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//所有请求都拦截 包括静态资源
                .excludePathPatterns("/login","/","/css/**","/js/**","/fonts/**","/images/**","/sql");
    }
}
