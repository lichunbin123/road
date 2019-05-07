package com.example.crud.controller;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.service.CrudService;
import com.example.crud.service.UserService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrudController {

    private CrudService crudService;
    private UserService userService;
    @Autowired
    public CrudController(CrudService crudService, UserService userService) {
        this.crudService = crudService;
        this.userService = userService;
    }

    @RequestMapping("/getAllBlogs")
    @ResponseBody
    public ResponseEntity<ResponseMessage> getAllBlog(){
        System.out.println("进入getAllBlogs的Controller");
        ResponseMessage responseMessage = crudService.findAllMessage();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
//    @RequestMapping("login")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage> login(@RequestBody ApplicationUser loginUser){
//        System.out.println("接收前端请求成功");
//        ResponseMessage responseMessage = userService.verify(loginUser);
//        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
//    }
    @RequestMapping ("register")
    @ResponseBody
    public ResponseEntity<ResponseMessage> register(@RequestBody ApplicationUser loginUser){
        System.out.println("接收前端请求成功");
        System.out.println("loginUserName==="+loginUser.getUsername());
        ResponseMessage responseMessage = userService.insert(loginUser);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
}
