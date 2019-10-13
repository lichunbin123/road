package com.example.crud.controller;

import com.example.crud.bean.Plan;
import com.example.crud.dao.PlanDao;
import com.example.crud.service.PlanService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/10/19  7:58 PM
 */
@Controller
@CrossOrigin
public class ResourceController {
    private PlanService planService;
    private PlanDao planDao;
    @Autowired
    public ResourceController(PlanService planService,PlanDao planDao){
        this.planService = planService;
        this.planDao = planDao;
    }
    @RequestMapping("/disposal/insertPlan")
    @ResponseBody
    public ResponseEntity<ResponseMessage> insertPlan(@RequestParam("file") MultipartFile file){
        System.out.println("进入到存储预案模块");
        ResponseMessage responseMessage = planService.insertPlan(file);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/disposal/selectPlan")
    @ResponseBody
    public ResponseEntity<ResponseMessage> selectPlanMessage(){
        ResponseMessage responseMessage = planService.selectPlanMessage();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/disposal/download/{name}")
    @ResponseBody
    public ResponseEntity<byte[]> downloadPlan(@PathVariable String name){
        System.out.println("进入到下载预案模块");
        String newname = name.replace("#",".");
        System.out.println("name==="+newname);
//        ResponseMessage responseMessage = planService.downloadPlan(newname);
//        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
        List<Plan> list =  planDao.downloadPlan(newname);
        if (list!=null && list.size()>0){
            System.out.println(list.get(0));
            return ResponseEntity.ok()
                    .body(list.get(0).getContent());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
