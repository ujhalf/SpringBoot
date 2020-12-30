package com.half.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/24 12:06
 * @Version 1.0
 * @Description
 */
//@Component
//@PropertySource("classpath:mycar.properties")
@ConfigurationProperties(prefix = "mycar")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    private String brand;
    private Integer price;

}
