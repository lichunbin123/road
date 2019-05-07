package com.example.crud.dao;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private UserMapper userMapper;
    @Autowired
    public UserDao(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public void insert(ApplicationUser user){
        userMapper.insert(user);
    }

    public List<ApplicationUser> findByName(String userName){
        return userMapper.findByName(userName);
    }

}
