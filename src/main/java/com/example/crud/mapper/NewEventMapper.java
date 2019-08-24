package com.example.crud.mapper;

import com.example.crud.bean.EventAnalysis;
import com.example.crud.bean.EventMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/20/19  2:23 PM
 */
@Component
public interface NewEventMapper {
    void insertNewEventMessage(EventMessage eventMessage);
    void insertAllEventMessage(EventMessage eventMessage);
    int searchcountofsuccess();
    int searchcountoffail();
    void deleteListOfSuccess();
    void deleteListOfFail();
    List<EventMessage> findAllEventMessage();
    List<EventMessage> searchlistofsuccess();
    List<EventMessage> searchlistoffail();
}
