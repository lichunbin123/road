package com.example.crud.controller;

import com.example.crud.service.BasicInfoService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: lcb
 * @Date: 2019  10/2/19  3:38 PM
 */
@Controller
public class BasicInfoController {
   private BasicInfoService basicInfoService;
   @Autowired
   public BasicInfoController(BasicInfoService basicInfoService){
       this.basicInfoService = basicInfoService;
   }
   @RequestMapping("/monitoring/bdmap/searchcity")
   @ResponseBody
   public ResponseEntity<ResponseMessage> selectCities(){
       System.out.println("select city");
       ResponseMessage responseMessage = basicInfoService.selectCities();
       return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
   }
    @RequestMapping("/monitoring/bdmap/searchcounty/{city}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> selectCounty(@PathVariable String city){
        System.out.println("select county");
        System.out.println("city==="+city);
        ResponseMessage responseMessage = basicInfoService.selectCounty(city);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/monitoring/bdmap/searchname/{value1}/{value2}/{value3}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> selectCounty(@PathVariable String value1,@PathVariable String value2,@PathVariable String value3){
        System.out.println("select name");
        System.out.println("value1==="+value1);
        System.out.println("value2==="+value2);
        System.out.println("value3==="+value3);
        String road = "路段";
        String bridge = "桥梁";
        String tunnel = "隧道";
        if (value3.equals(road)){
            ResponseMessage responseMessage = basicInfoService.selectRoadAll(value2);
            return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
        }else if (value3.equals(bridge)){
            System.out.println("进入桥梁模块");
            ResponseMessage responseMessage = basicInfoService.selectBridgeAll(value2);
            return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
        }else if (value3 == tunnel){
            ResponseMessage responseMessage = basicInfoService.selectTunnelAll(value2);
            return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
        }
        System.out.println("没有匹配上");
        return null;
    }
    @RequestMapping("/monitoring/bdmap/searchinfo/{value1}/{value2}/{value3}/{value4}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> selectInfo(@PathVariable String value1,@PathVariable String value2,@PathVariable String value3,@PathVariable String value4){
        System.out.println("select name");
        System.out.println("value1==="+value1);
        System.out.println("value2==="+value2);
        System.out.println("value3==="+value3);
        System.out.println("value4==="+value4);
        String road = "路段";
        String bridge = "桥梁";
        String tunnel = "隧道";
        if (value3.equals(road)){
            ResponseMessage responseMessage = basicInfoService.selectRoadInfo(value2,value4);
            return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
        }else if (value3.equals(bridge)){
            System.out.println("进入桥梁模块");
            ResponseMessage responseMessage = basicInfoService.selectBridgeInfo(value2,value4);
            return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
        }else if (value3 == tunnel){
            ResponseMessage responseMessage = basicInfoService.selectTunnelInfo(value2,value4);
            return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
        }
        System.out.println("没有匹配上");
        return null;
    }
}
