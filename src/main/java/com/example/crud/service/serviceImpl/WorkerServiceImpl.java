package com.example.crud.service.serviceImpl;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.bean.Worker;
import com.example.crud.dao.WorkerDao;
import com.example.crud.service.WorkerService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/14/19  11:54 AM
 */
@Component
public class WorkerServiceImpl implements WorkerService {
    private WorkerDao workerDao;
    @Autowired
    public WorkerServiceImpl(WorkerDao workerDao){
        this.workerDao = workerDao;
    }
    @Override
    public ResponseMessage insertWorker(List<Worker> workers) {
        if (workers!=null){
            System.out.println("list.get(0).getClassname()==="+workers.get(0).getClassname());
            workerDao.insertWorker(workers);
            return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("添加成功").build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("添加失败").build();
        }
    }

    @Override
    public ResponseMessage findAllWorker(String classname) {
        List<Worker> list = workerDao.findAllWorker(classname);
        if (list!=null&&list.size()>0){
            return ResponseMessage.<Worker>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("员工信息为空").build();
        }
    }
}
