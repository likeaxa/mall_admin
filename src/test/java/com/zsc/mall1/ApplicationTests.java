package com.zsc.mall1;

import com.zsc.mall1.bean.User;
import com.zsc.mall1.redis.RedisTemplateService;
import com.zsc.mall1.util.JwtUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplateService redisTemplateService;
    @Test
    public void test()  {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");

    }

    @Test
    public void redisTest(){

        User user = new User();
        user.setId(11);
        user.setUsername("test");
        user.setPassword("hello redis");
        redisTemplateService.set("key1",user);
        User us = redisTemplateService.get("key1",User.class);
        System.out.println(us.getUsername()+":  "+us.getPassword());
    }

    @Test
    public void  JWTTest() throws Exception {
        String username="张三";

        String  jwt = JwtUtil.CreatJWT(username);
        System.out.println(jwt);

        String user = JwtUtil.parseJWT(jwt);

        System.out.println(user);
    }
}
