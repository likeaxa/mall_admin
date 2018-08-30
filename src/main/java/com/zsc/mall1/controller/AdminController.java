package com.zsc.mall1.controller;

import com.zsc.mall1.bean.Admin;
import com.zsc.mall1.service.AdminService;
import com.zsc.mall1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


   private  AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public String login(@RequestBody() Admin admin){
        if(adminService.loginCheck(admin.getAdminname(),admin.getPassword())){
            return  JwtUtil.CreatJWT(admin.getAdminname());
        }else {
            return "-1";
        }
    }

}
