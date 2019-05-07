package com.example.crud;

import com.example.crud.bean.Crud;
import com.example.crud.dao.CrudDao;
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
    CrudDao crudDao;
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
    public void test01(){
        List<Crud> crudList = crudDao.findAll();
        //默认保存对象采用的是jdk序列化机制，序列化之后的数据保存在redis中
//        redisTemplate.opsForValue().set("crud",crudList);
        //将数据以json的形式保存
        redisTemplate.opsForValue().set("crud",crudList);
        List<Crud> list = (List<Crud>) redisTemplate.opsForValue().get("crud");
        System.out.println("list===="+list.toString());
    }
    @Test
    public void contextLoads() {

    }

}
