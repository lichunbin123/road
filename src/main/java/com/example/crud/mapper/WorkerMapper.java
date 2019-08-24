package com.example.crud.mapper;

import com.example.crud.bean.Worker;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/14/19  10:51 AM
 */
@Component
public interface WorkerMapper {
    void insertWorker(List<Worker> list);
    List<Worker> findAllWorker(String classname);
}
