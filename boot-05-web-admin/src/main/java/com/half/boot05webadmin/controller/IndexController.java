package com.half.boot05webadmin.controller;

import com.half.boot05webadmin.bean.Account;
import com.half.boot05webadmin.bean.User;

import com.half.boot05webadmin.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/25 22:25
 * @Version 1.0
 * @Description
 */
@Slf4j
@Controller
public class IndexController {

    HandlerMethodReturnValueHandlerComposite composite;

    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {
        if (!StringUtils.isEmpty(user.getUserName()) && !StringUtils.isEmpty(user.getPassword())) {
            session.setAttribute("loginUser", user);
            //防止表单重复提交
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "账号密码错误");
            return "login";
        }

    }

    @GetMapping("/main.html")
    public String mainPage() {
        log.info("当前方式是:{}", "mainOPage");
        return "main";
    }

    /**
     * 测试下5xx的异常是否会正常跳转到错误页面
     */
    @ResponseBody
    @GetMapping("/testError")
    public String test() {
        int a = 5 / 0;
        return "helloworld";
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/sql")
    /*测试druid的监控*/
    public String query() {
        Long count = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
        System.out.println(count);
        return count.toString();
    }

    @Autowired
    AccountMapper accountMapper;
    @ResponseBody
    @GetMapping("/user/{id}")
    public Account findOne(@PathVariable("id")Integer id) {

    return accountMapper.findOne(id);
    }
}
