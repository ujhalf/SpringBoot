package com.half.boot05webadmin.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRegistration;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/28 16:09
 * @Version 1.0
 * @Description
 */
@Configuration
public class MyRegistConfig {

    //使用RegistrationBean进行注册组件
    @Bean
    public ServletRegistrationBean myServlet() {
        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean(myServlet, "/my");
    }
}
