package com.wei.worm.security;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Component
public class UserValidateInterecptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//      获取request的cookie
        String requestType = request.getHeader("X-Requested-With");
        HttpSession httpSession=request.getSession(false);

        if (null==httpSession) {
            System.out.println("没有session===跳转到登录页面");
            response.sendRedirect(request.getContextPath()+"/index.html");
        } else {
            if(httpSession.getAttribute("isLogin")!=null&&"1".equals(httpSession.getAttribute("isLogin").toString())) {
                return true;
            }else if("XMLHttpRequest".equals(requestType)){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.sendError(403);
                return false;
            }
            response.sendRedirect(request.getContextPath()+"/index.html");
        }

        return false;

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.print("post...");
    }
    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.print("after");

    }
}