package com.ujhalf.hello.auto;

import com.ujhalf.hello.bean.HelloProperties;
import com.ujhalf.hello.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/30 13:42
 * @Version 1.0
 * @Description
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoconfiguration {

    @ConditionalOnMissingBean(HelloService.class)
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}
