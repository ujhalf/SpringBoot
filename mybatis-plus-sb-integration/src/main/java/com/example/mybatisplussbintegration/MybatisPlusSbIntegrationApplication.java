package com.example.mybatisplussbintegration;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mybatisplussbintegration.mapper")
public class MybatisPlusSbIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusSbIntegrationApplication.class, args);
    }

}
