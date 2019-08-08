package com.example.crud.service;

import com.example.crud.utils.ResponseMessage;

/**
 * @author: lcb
 * @Date: 2019  8/4/19  12:40 PM
 */
public interface DataService {
    ResponseMessage producer();
    ResponseMessage consumer();
}
