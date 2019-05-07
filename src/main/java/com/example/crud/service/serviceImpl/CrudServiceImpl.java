package com.example.crud.service.serviceImpl;

import com.example.crud.bean.Crud;
import com.example.crud.dao.CrudDao;
import com.example.crud.service.CrudService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CrudServiceImpl implements CrudService {

    private final CrudDao crudDao;

    @Autowired
    public CrudServiceImpl(CrudDao crudDao) {
        this.crudDao = crudDao;
    }

    @Override
    public ResponseMessage findAllMessage() {
        List<Crud> list = crudDao.findAll();
        return ResponseMessage.<Crud>builder().successStatus(true).responseData(list)
                        .httpStatus(HttpStatus.OK).build();
    }
}
