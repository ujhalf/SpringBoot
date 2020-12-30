package com.half.boot05webadmin.controller;

import com.half.boot05webadmin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/25 23:17
 * @Version 1.0
 * @Description
 */
@Controller
public class TableController {

    @GetMapping("/basic_table")
    public String basic_table() {
        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model) {
        List<User> users = Arrays.asList(
                new User("zhangsan", "123"),
                new User("lisi", "lisi"),
                new User("wangwu", "wangwu"),
                new User("kobe", "bryant")
        );
        model.addAttribute("users",users);

        return "table/dynamic_table";
    }

    @GetMapping("/editable_table")
    public String editable_table() {
        return "table/editable_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table() {
        return "table/responsive_table";
    }

    @GetMapping("/pricing_table")
    public String pricing_table() {
        return "table/pricing_table";
    }
}


