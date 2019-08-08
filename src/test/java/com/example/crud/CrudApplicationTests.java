package com.example.crud;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudApplicationTests {
    @Autowired
    UserDao userDao;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Test
    public void test(){
        stringRedisTemplate.opsForValue().append("msg","hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println("msg====="+msg);
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void test02(){
        List<ApplicationUser> list = userDao.findAll();
        System.out.println(list.get(0));
    }

}
