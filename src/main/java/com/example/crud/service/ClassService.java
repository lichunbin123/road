package com.example.crud.service;

import com.example.crud.bean.ClassMessage;
import com.example.crud.bean.ClassMessageNew;
import com.example.crud.utils.ResponseMessage;

/**
 * @author: lcb
 * @Date: 2019  8/11/19  7:42 PM
 */
public interface ClassService {
    ResponseMessage insertClassMessage(ClassMessage classMessage);
    ResponseMessage deleteClassMessage();
    ResponseMessage findAllClassMessage();
}
