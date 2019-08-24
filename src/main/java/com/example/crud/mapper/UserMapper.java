package com.example.crud.mapper;

import com.example.crud.bean.ApplicationUser;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    List<ApplicationUser> findByName(String username) throws DataAccessException;
    List<ApplicationUser> findAll() throws DataAccessException;
    void insertUserMessage(ApplicationUser user);
    void deleteUserMessage(int id);
    void updateUserMessage(String password,String role,String username);
    List<ApplicationUser> findById(int id);
    String findRole(String username);
}
