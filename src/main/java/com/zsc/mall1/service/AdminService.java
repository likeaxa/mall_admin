package com.zsc.mall1.service;

import com.zsc.mall1.bean.Admin;
import com.zsc.mall1.mapper.AdminMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService   {


   private  AdminMapper adminMapper;

   @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public boolean loginCheck(String adminname, String password){
        try {
            List<Admin> admins = adminMapper.getList();
            for(Admin admin:admins){
                if(admin.getAdminname().equals(adminname)&&
                        admin.getPassword().equals(password)){
                    return true;
                }
            }
            return false;

        }catch (Exception e){
            return  false;
        }

    }
}
