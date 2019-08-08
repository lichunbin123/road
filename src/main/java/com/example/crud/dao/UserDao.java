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

    public List<ApplicationUser> findByName(String userName){
        return userMapper.findByName(userName);
    }

    public List<ApplicationUser> findAll(){
        return userMapper.findAll();
    }

    public void insertUserMessage(ApplicationUser user){
        userMapper.insertUserMessage(user);
    }

    public void deleteUserMessage(int id){
        userMapper.deleteUserMessage(id);
    }

    public void updateUserMessage(String password,String role,String username){
        userMapper.updateUserMessage(password,role,username);
    }

    public List<ApplicationUser> findById(int id){
        return userMapper.findById(id);
    }
}
