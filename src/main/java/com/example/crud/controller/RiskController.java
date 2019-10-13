package com.example.crud.controller;

import com.example.crud.bean.ClassMessage;
import com.example.crud.bean.RiskRecord;
import com.example.crud.service.RiskService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: lcb
 * @Date: 2019  9/27/19  3:43 PM
 */
@Controller
public class RiskController {
    private RiskService riskService;
    @Autowired
    public RiskController(RiskService riskService){
        this.riskService = riskService;
    }

    @RequestMapping("/disposal/addrisk")
    @ResponseBody
    public ResponseEntity<ResponseMessage> addRiskRecord(@RequestBody RiskRecord riskRecord){
        System.out.println("进入添加风险点记录模块");
        ResponseMessage responseMessage = riskService.insertRiskRecord(riskRecord);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/disposal/searchriskrecord")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchRiskRecord(){
        ResponseMessage responseMessage = riskService.findAllRiskRecord();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/disposal/riskmanage/search/{input}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchRisk(@PathVariable String input){
        ResponseMessage responseMessage = riskService.searchRisk(input);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
}
