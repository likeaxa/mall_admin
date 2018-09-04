package com.zsc.mall1;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zsc.mall1.bean.User;
import com.zsc.mall1.redis.RedisService;
import com.zsc.mall1.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mall1ApplicationTests {


    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Test
    public void  aaa(){
        Map<String ,String> valueMap = new HashMap<>();
        PageHelper.startPage(1,5,"id desc");
        List<User> users = userService.getAllUser();

        Gson gs = new Gson();
        String  s =gs.toJson(new PageInfo<>(users,5));
        valueMap = gs.fromJson(s, new TypeToken<Map<String, String>>() {}.getType());

    }

}
