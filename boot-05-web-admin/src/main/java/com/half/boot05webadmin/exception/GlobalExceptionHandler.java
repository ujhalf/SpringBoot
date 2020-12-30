package com.half.boot05webadmin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/28 15:25
 * @Version 1.0
 * @Description
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})
    private String handleArithException(Exception ex) {
        log.error("使用自定义的异常处理器:");
        log.error("异常是:{}", ex);
        //返回view
        return "login";
    }
}
