package com.half.yamlusecases;

import com.half.yamlusecases.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class YamlUsecasesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(YamlUsecasesApplication.class, args);
        Person bean = ctx.getBean(Person.class);
        System.out.println(bean);
    }

}
