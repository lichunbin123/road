package com.example.crud.dao;

import com.example.crud.bean.Crud;
import com.example.crud.mapper.CrudMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrudDao{

    private CrudMapper crudMapper;

    @Autowired
    public CrudDao(CrudMapper crudMapper) {
        this.crudMapper = crudMapper;
    }
    @Cacheable(cacheNames = "crud")
    public List<Crud> findAll() throws DataAccessException {
        System.out.println("从磁盘查询数据");
        return crudMapper.findAll();
    }
}
