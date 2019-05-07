package com.example.crud.mapper;

import com.example.crud.bean.Crud;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CrudMapper {
    List<Crud> findAll() throws DataAccessException;
}
