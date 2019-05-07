package com.example.crud.service;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.utils.ResponseMessage;

public interface UserService {
    ResponseMessage insert(ApplicationUser user);
    ResponseMessage verify(ApplicationUser loginUser);
    ResponseMessage error();
}
