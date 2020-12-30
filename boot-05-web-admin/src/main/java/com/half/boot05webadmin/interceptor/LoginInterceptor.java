package com.half.boot05webadmin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/26 15:03
 * @Version 1.0
 * @Description
 *
 * 登陆检查
 * 1.配置拦截器的拦截路径
 * 2.将拦截器加入容器
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        log.info("preHandle()拦截的请求路径是:{}",requestURI);
        HttpSession session=request.getSession();
        if (session.getAttribute("loginUser") != null) {
            System.out.println("拦截器放行");
            return true;
        }
        System.out.println("拦截器拦截");
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("postHandle()拦截的请求路径是:{}",requestURI);
        log.info("postHandle() ModelAndView:{}",modelAndView);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("afterCompletion()拦截的请求路径是:{}",requestURI);
        log.info("afterCompeltion()出现异常:{}",ex);
    }
}
