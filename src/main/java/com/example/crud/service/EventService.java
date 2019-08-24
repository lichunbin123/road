package com.example.crud.service;

import com.example.crud.bean.InitEventMessage;
import com.example.crud.utils.ResponseMessage;

/**
 * @author: lcb
 * @Date: 2019  8/19/19  9:43 PM
 */
public interface EventService {
    ResponseMessage insertEventMessage(InitEventMessage initEventMessage);
    ResponseMessage findEventMessage();
    ResponseMessage deleteEventMessage(int id);
}
