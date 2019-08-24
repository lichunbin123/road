package com.example.crud.dao;

import com.example.crud.bean.ClassMessageNew;
import com.example.crud.mapper.ClassMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/11/19  7:33 PM
 */
@Repository
public class ClassMessageDao {
    private ClassMessageMapper classMessageMapper;
    @Autowired
    public ClassMessageDao(ClassMessageMapper classMessageMapper){
        this.classMessageMapper = classMessageMapper;
    }
    public void insertClassMessage(ClassMessageNew classMessageNew){
        System.out.println("dao==="+classMessageNew.getNewclassname());
        classMessageMapper.insertClassMessage(classMessageNew);
    }
    public void deleteClassMessage(){
        classMessageMapper.deleteClassMessage();
    }
    public List<ClassMessageNew> findAllClassMessage(){
        return classMessageMapper.findAllClassMessage();
    }
}
