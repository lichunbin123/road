package com.example.crud.dao;

import com.example.crud.bean.InitEventMessage;
import com.example.crud.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/19/19  9:10 PM
 */
@Repository
public class EventDao {
    private EventMapper eventMapper;
    @Autowired
    public EventDao(EventMapper eventMapper){
        this.eventMapper = eventMapper;
    }

    public void insertEventMessage(InitEventMessage initEventMessage){
        eventMapper.insertEventMessage(initEventMessage);
    }

    public List<InitEventMessage> findEventMessage(){
        return eventMapper.findEventMessage();
    }

    public void deleteEventMessage(int id){
        eventMapper.deleteEventMessage(id);
    }

    public int searchEventCount(){
        return eventMapper.searchcount();
    }
}
