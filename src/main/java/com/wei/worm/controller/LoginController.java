package com.wei.worm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam String name, @RequestParam String pwd, HttpServletRequest request, HttpServletResponse response){
        if("admin".equals(name)&&"1".equals(pwd)){
            if(request.getSession(false)==null){
               HttpSession session= request.getSession();
               session.setAttribute("isLogin","1");
               session.setMaxInactiveInterval(600);
            }
            return "success";
        }
        return "err";
    }
}
