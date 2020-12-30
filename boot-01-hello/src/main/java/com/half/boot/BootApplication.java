package com.half.boot;

import com.half.boot.bean.Car;
import com.half.boot.bean.User;
import com.half.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/24 10:12
 * @Version 1.0
 * @Description
 */
@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootApplication.class, args);
//        String[] names = context.getBeanDefinitionNames();
//        for (int i = 0; i < names.length; i++) {
//            System.out.println(names[i]);
//        }
        MyConfig myConfig = context.getBean("myConfig", MyConfig.class);
        System.out.println(myConfig);

        User user = context.getBean(User.class);
        Car car = context.getBean(Car.class);
        System.out.println(car);
    }
}
