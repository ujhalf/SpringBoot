package com.example.mybatisplussbintegration.domain;

import lombok.Data;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/29 12:18
 * @Version 1.0
 * @Description
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}