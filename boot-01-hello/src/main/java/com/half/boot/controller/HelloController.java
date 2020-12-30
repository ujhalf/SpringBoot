package com.half.boot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/24 10:13
 * @Version 1.0
 * @Description
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam("name") String name) {
        return "Hello "+name;
    }
}
