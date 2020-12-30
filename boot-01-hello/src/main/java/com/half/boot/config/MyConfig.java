package com.half.boot.config;

import com.half.boot.bean.Car;
import com.half.boot.bean.Pet;
import com.half.boot.bean.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/24 11:32
 * @Version 1.0
 * @Description
 */
@EnableConfigurationProperties(Car.class)
@Configuration
public class MyConfig {
    /**
     * 方法名为bean id
     */
    @Bean
    public User user() {
        return new User("lebron", 35);
    }

    @Bean("tom")
    public Pet tomcatCat() {
        return new Pet("tomcat");
    }
//    @Bean
//    public Car car(){
//        return new Car();
//    }
}
