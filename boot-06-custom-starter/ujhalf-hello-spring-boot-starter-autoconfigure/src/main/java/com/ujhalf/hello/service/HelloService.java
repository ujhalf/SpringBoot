package com.ujhalf.hello.service;

import com.ujhalf.hello.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/30 13:37
 * @Version 1.0
 * @Description
 */

public class HelloService {

    @Autowired
    HelloProperties properties;

    public String sayHello(String username) {
        return properties.getPrefix() + ":" + username + ">>" + properties.getSuffix();

    }
}
