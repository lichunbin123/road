package com.example.crud.dao;

import com.example.crud.bean.Worker;
import com.example.crud.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/14/19  11:32 AM
 */
@Repository
public class WorkerDao {
    private WorkerMapper workerMapper;
    @Autowired
    public WorkerDao(WorkerMapper workerMapper){
        this.workerMapper = workerMapper;
    }
    public void insertWorker(List<Worker> workers){
        workerMapper.insertWorker(workers);
    }
    public List<Worker> findAllWorker(String classname){
        return workerMapper.findAllWorker(classname);
    }
}
