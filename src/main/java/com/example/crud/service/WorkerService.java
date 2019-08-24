package com.example.crud.service;

import com.example.crud.bean.Worker;
import com.example.crud.utils.ResponseMessage;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/14/19  11:38 AM
 */
public interface WorkerService {
    ResponseMessage insertWorker(List<Worker> workers);
    ResponseMessage findAllWorker(String classname);
}
