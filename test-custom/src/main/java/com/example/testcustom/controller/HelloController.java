package com.example.testcustom.controller;

import com.ujhalf.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/30 13:51
 * @Version 1.0
 * @Description
 */
@RestController
public class HelloController {

    @Autowired
    HelloService service;
    @GetMapping("/hello")
    public String sayHello(String username) {
        return service.sayHello(username);
    }
}
