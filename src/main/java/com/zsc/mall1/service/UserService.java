package com.zsc.mall1.service;

import com.zsc.mall1.bean.User;
import com.zsc.mall1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


   private UserMapper userMapper;

   @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User>  getAllUser(){
        List<User> users = userMapper.getList();
        return returnToController(users);
    }

    public List<User> getSimpleUser(String name){
        List<User> users = userMapper.getSimpleUserByName(name);
        return  returnToController(users);
    }
    public User getUserById(int id){
        User user = userMapper.getUserById(id);
       return returnToController(user);
    }

    public boolean insertUser(User user){
        try {
            userMapper.insert(user);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean updateUser(User user){
        try {
            userMapper.update(user);
            return  true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean deleteUser(int id){
        try {
            userMapper.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }
    private  <T> T returnToController(T value){
        if(value==null){
            return null;
        }
        else{
            return value;
        }
    }
}


