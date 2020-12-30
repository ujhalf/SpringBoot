package com.half.boot05webadmin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/29 11:04
 * @Version 1.0
 * @Description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    Integer id;
    String username;
    private Date birthday;
    private String sex;
    private String address;


}
