package com.example.crud.controller;

import com.example.crud.bean.ClassMessage;
import com.example.crud.bean.Worker;
import com.example.crud.service.ClassService;
import com.example.crud.service.WorkerService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static javax.print.attribute.standard.MediaPrintableArea.MM;
import static javax.swing.text.html.HTML.Tag.DD;

/**
 * @author: lcb
 * @Date: 2019  8/11/19  4:41 PM
 */
@Controller
public class ClassController {
    private ClassService classService;
    private WorkerService workerService;
    @Autowired
    public ClassController(ClassService classService, WorkerService workerService){
        this.classService = classService;
        this.workerService = workerService;

    }
    @RequestMapping("/application/addclass")
    @ResponseBody
    public ResponseEntity<ResponseMessage> addClass(@RequestBody ClassMessage classMessage){
        System.out.println("进入增加班次模块");
        ResponseMessage responseMessage = classService.insertClassMessage(classMessage);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/application/searchmessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchmessage(){
        System.out.println("进入查询班次信息模块");
        ResponseMessage responseMessage = classService.findAllClassMessage();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/application/addworker")
    @ResponseBody
    public ResponseEntity<ResponseMessage> addworker(@RequestBody List<Worker> workers){
        System.out.println("进入添加值班人员模块");

//        for (Worker worker : workers) {
//            System.out.println(worker.getClassname());
//        }
        ResponseMessage responseMessage = workerService.insertWorker(workers);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
//        return null;
    }
    @RequestMapping("/application/searchworker")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchworker(@RequestParam(value = "classname") String classname){
        System.out.println("classname==="+classname);
        System.out.println("进入员工信息模块");
        ResponseMessage responseMessage = workerService.findAllWorker(classname);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
}
