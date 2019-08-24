package com.example.crud.service;

import com.example.crud.bean.EventMessage;
import com.example.crud.utils.ResponseMessage;

/**
 * @author: lcb
 * @Date: 2019  8/20/19  2:35 PM
 */
public interface NewEventService {
    ResponseMessage insertNewEventMessage(EventMessage eventMessage);
    ResponseMessage insertAllEventMessage(EventMessage eventMessage);
    ResponseMessage searchEventCountOfSuccess();
    ResponseMessage searchEventCountOfFail();
    ResponseMessage findAllEventMessage();
    ResponseMessage searchListOfSuccess();
    ResponseMessage searchListOfFail();
    ResponseMessage deleteListOfSuccess();
    ResponseMessage deleteListOfFail();
    ResponseMessage searchForYear(String year);
    ResponseMessage searchForMonth(String month);
    ResponseMessage searchForDefault(String now);
}
