package com.zsc.mall1;

import com.zsc.mall1.bean.User;
import com.zsc.mall1.redis.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mall1ApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisService redisService;
    @Test
    public void contextLoads () {

            stringRedisTemplate.opsForValue().set("1","流年划破容颜");

            Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("1"));


    }

    @Test
    public void redisText(){
        User user = new User();
        user.setPassword("123");
        user.setId(11);
        user.setUsername("hello redis");

        boolean flag =redisService.set("key1",user);
        System.out.println(flag);
        User us = redisService.get("key1",User.class);
        System.out.println(us.getUsername()+":   "+us.getPassword());
    }

}
