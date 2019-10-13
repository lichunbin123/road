package com.example.crud.controller;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.service.DataService;
import com.example.crud.service.UserService;
import com.example.crud.utils.ResponseMessage;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lcb
 * @Date: 2019  6/17/19  9:11 PM
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @RequestMapping("/application")
    @ResponseBody
    public void EnterApplication(){
        System.out.println("had enter application page");
    }
    @RequestMapping("/application/mainofuser")
    @ResponseBody
    public void EnterApplicationOfUser(){
        System.out.println("had enter application page of user");
    }
    @RequestMapping("/monitoring/mainofuser")
    @ResponseBody
    public void EnterMonitoringOfUser(){
        System.out.println("had enter monitoring page of user");
    }
    @RequestMapping("/publish")
    @ResponseBody
    public void EnterPublish(){
        System.out.println("had enter publish page");
    }
    @RequestMapping("/disposal")
    @ResponseBody
    public void EnterDisposal(){
        System.out.println("had enter disposal page");
    }

    @RequestMapping("/getUserMessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> getUserMessage(){
        ResponseMessage responseMessage = userService.findAllMessage();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());

    }
    @RequestMapping("/addUserMessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> addUserMessage(@RequestBody ApplicationUser user){
        ResponseMessage responseMessage = userService.insertUserMessage(user);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/deleteUserMessage/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteUserMessage(@PathVariable int id){
        ResponseMessage responseMessage = userService.deleteUserMessage(id);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/updateUserMessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> updateUserMessage(@RequestBody ApplicationUser user){

        ResponseMessage responseMessage = userService.updateUserMessage(user);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/getBlogById/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> findById(@PathVariable int id){
        System.out.println("had enter findById and id ==="+id);
        ResponseMessage responseMessage = userService.findById(id);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/findrole")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchworker(@RequestParam(value = "username") String username){
        ResponseMessage responseMessage = userService.findRole(username);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
}
