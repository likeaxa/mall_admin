package com.zsc.mall1.controller;


import com.zsc.mall1.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @RequestMapping("/login")
    public String login(){
        return "AdminLogin";
    }
    @RequestMapping("/")
    public String foreHome(){
        return "AdminLogin";
    }

    @RequestMapping("/adminmanager")
    public String  adminname(){
        return  "AdminManager";
    }

    @RequestMapping("/bookmanager")
    public String bookMagager() {

        return "BookManager";
    }
    @RequestMapping("/usermanager")
    public String userManager(){

        return "UserManager";
    }

}
