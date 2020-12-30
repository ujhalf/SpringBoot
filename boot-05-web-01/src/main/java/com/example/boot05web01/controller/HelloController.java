package com.example.boot05web01.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/24 21:14
 * @Version 1.0
 * @Description
 */
@RestController
public class HelloController {

    @GetMapping("/user")
    public String getUser() {
        return "get user";
    }

    @PostMapping("/user")
    public String postUser() {
        return "post user";
    }
    @PutMapping("/user")
    public String putUser() {
        return "put user";
    }

    @DeleteMapping("/user")
    public String deletetUser() {
        return "delete user";
    }
}
