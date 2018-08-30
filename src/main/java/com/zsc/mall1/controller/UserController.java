package com.zsc.mall1.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsc.mall1.bean.User;
import com.zsc.mall1.service.UserService;
import com.zsc.mall1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {


    private   UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("admin/user")
    public PageInfo<User> getUser(@RequestParam(value = "username",defaultValue = "请输入需要查找用户") String username ,
                                  @RequestParam(value = "start", defaultValue = "1") int start,
                                  @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{

            if(!username.equals("请输入需要查找用户")){
                PageHelper.startPage(start,size,"id desc");
                List<User> users = userService.getSimpleUser(username);
                return new PageInfo<>(users,5);
            }else {
                PageHelper.startPage(start,size,"id desc");
                List<User> users=userService.getAllUser();
                return  new PageInfo<>(users,5); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
            }


    }



    @GetMapping("/admin/userById")
    public User getUserById(@RequestParam(value = "userId") Integer id){
        return  userService.getUserById(id);
    }
    @PutMapping("/admin/user")
    public String  insertUser(@RequestBody User user){

       // System.out.println(user.getUsername());
        boolean flag =userService.insertUser(user);
        return resultToView(flag);
    }

    @PostMapping("/admin/user")
    public  String updateUser(@RequestBody User user){
     //   System.out.println(user.getUsername());
        boolean flag =userService.updateUser(user);
        return resultToView(flag);
    }
    @DeleteMapping("/admin/user")
    public String deleteUser(@RequestParam(value = "userId") Integer id){
        boolean flag =userService.deleteUser(id);
        return resultToView(flag);
    }

    private String  resultToView(boolean flag) {
        if(flag){
            return "ok";
        }
        else {
            return "-1";
        }
    }

}


