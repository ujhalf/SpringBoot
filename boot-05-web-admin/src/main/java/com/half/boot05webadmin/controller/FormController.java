package com.half.boot05webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/26 16:02
 * @Version 1.0
 * @Description
 */
@Slf4j
@Controller
public class FormController {
    @GetMapping("/form_layouts")
    public String form_layouts() {
        return "form/form_layouts";
    }

    /*使用multipartFile自动封装上传的文件**/
    @PostMapping("/upload")
    public String upload(String email, String username, MultipartFile headerImg, MultipartFile[] photos) throws IOException {
        log.info("上传的信息:email={},usernmae={},headerImg={},photos={}",
                email, username, headerImg.getSize(), photos.length);
        //将文件保存至服务器
        if (!headerImg.isEmpty()) {
            headerImg.transferTo(new File("D:\\cache\\" + headerImg.getOriginalFilename()));
        }
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    photo.transferTo(new File("D:\\cache\\" + photo.getOriginalFilename()));
                }
            }
        }
        return "main";
    }
}
