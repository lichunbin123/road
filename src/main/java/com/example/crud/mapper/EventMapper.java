package com.example.crud.mapper;

import com.example.crud.bean.InitEventMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/19/19  8:40 PM
 */
@Component
public interface EventMapper {
    void insertEventMessage(InitEventMessage initEventMessage);
    List<InitEventMessage> findEventMessage();
    void deleteEventMessage(int id);
    int searchcount();
}
