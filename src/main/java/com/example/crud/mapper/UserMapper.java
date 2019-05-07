package com.example.crud.mapper;

import com.example.crud.bean.ApplicationUser;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    void insert(ApplicationUser user) throws DataAccessException;
    List<ApplicationUser> findByName(String username) throws DataAccessException;
}
