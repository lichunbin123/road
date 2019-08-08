package com.example.crud.controller;

import com.example.crud.service.DataService;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: lcb
 * @Date: 2019  7/10/19  11:09 AM
 */
@Controller
public class DataController {
    private DataService dataService;
    @Autowired
    public DataController(DataService dataService){
        this.dataService = dataService;
    }
    @MessageMapping("/hello")
    @SendTo("/topic/chat")
    public String returnData() throws Exception{
        return "niyehao";
    }
    @RequestMapping("/monitoring")
    @ResponseBody
    public void EnterMonitoring(){
        System.out.println("进入监控模块");
    }
    @RequestMapping("/trafficMonitor")
    @ResponseBody
    public void trafficMonitor(){
        System.out.println("进入实时流量模块");
//        dataService.producer();
//        dataService.consumer();
    }
}
