package com.example.crud.controller;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.service.UserService;
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
 * @Date: 2019  6/17/19  9:11 PM
 */
@Controller
public class UserManager {

    private UserService userService;

    @Autowired
    public UserManager(UserService userService){
        this.userService = userService;
    }
    @RequestMapping("/application")
    @ResponseBody
    public void EnterApplication(){
        System.out.println("had enter application page");
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
    @RequestMapping("/updateUserMessage/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> updateUserMessage(@RequestBody ApplicationUser user,@PathVariable int id){
//        ResponseMessage responseMessage = userService.updateUserMessage(user);
//        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
        return null;
    }
    @RequestMapping("/getBlogById/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> findById(@PathVariable int id){
        System.out.println("had enter findById and id ==="+id);
        ResponseMessage responseMessage = userService.findById(id);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
}
