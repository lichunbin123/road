package com.example.crud.service;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.utils.ResponseMessage;

public interface UserService {
    ResponseMessage findAllMessage();
    ResponseMessage error();
    ResponseMessage insertUserMessage(ApplicationUser user);
    ResponseMessage deleteUserMessage(int id);
    ResponseMessage updateUserMessage(ApplicationUser user);
    ResponseMessage findById(int id);
}
