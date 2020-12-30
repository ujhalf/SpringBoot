package com.example.boot09featuresprofile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/30 8:54
 * @Version 1.0
 * @Description
 */
@RestController
public class HelloController {

    @Value("${name}")
    String name;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello: " + name;
    }

    @Value("${M2_HOME}")
    String msg;

    /**
     * 从环境变量获取属性
     */
    @GetMapping("/msg")
    public String getMsg() {
        return msg;
    }

    /**
     * 获取系统遍历
     **/
    @Value("${os.name}")
    String osName;

    @GetMapping("/sysPro")
    public String getSystemProperties() {
        return osName;
    }

    @Value("${java.version}")
    String javaVersion;

    @GetMapping("/javaVersion")
    public String getJavaVersion() {
        return javaVersion;
    }
}
