package com.example.testcustom.config;

import com.ujhalf.hello.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/30 13:59
 * @Version 1.0
 * @Description
 */
@Configuration
public class MyConfig {
    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}
