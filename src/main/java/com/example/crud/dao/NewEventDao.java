package com.example.crud.dao;

import com.example.crud.bean.EventAnalysis;
import com.example.crud.bean.EventMessage;
import com.example.crud.mapper.EventAnalysisMapper;
import com.example.crud.mapper.NewEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/20/19  2:32 PM
 */
@Repository
public class NewEventDao {
    private NewEventMapper newEventMapper;
    private EventAnalysisMapper eventAnalysisMapper;
    @Autowired
    public NewEventDao(NewEventMapper newEventMapper,EventAnalysisMapper eventAnalysisMapper){
        this.newEventMapper = newEventMapper;
        this.eventAnalysisMapper = eventAnalysisMapper;
    }
    public void insertNewEventMessage(EventMessage eventMessage){
        newEventMapper.insertNewEventMessage(eventMessage);
    }
    public void insertAllEventMessage(EventMessage eventMessage){
        newEventMapper.insertAllEventMessage(eventMessage);
    }
    public int searchEventCountOfSuccess(){
        return newEventMapper.searchcountofsuccess();
    }

    public int searchEventCountOfFail(){
        return newEventMapper.searchcountoffail();
    }

    public List<EventMessage> findAllEventMessage(){
        return newEventMapper.findAllEventMessage();
    }

    public List<EventMessage> searchListOfSuccess(){
        return newEventMapper.searchlistofsuccess();
    }

    public List<EventMessage> searchListOfFail(){
        return newEventMapper.searchlistoffail();
    }

    public void deleteListOfSuccess(){
        newEventMapper.deleteListOfSuccess();
    }

    public void deleteListOfFail(){
        newEventMapper.deleteListOfFail();
    }

    public List<EventAnalysis> searchForYear(String year){
        return eventAnalysisMapper.searchforyear(year);
    }

    public List<EventAnalysis> searchForMonth(String month){
        return eventAnalysisMapper.searchformonth(month);
    }

    public List<EventAnalysis> searchForDefault(String now){
        return eventAnalysisMapper.searchfordefault(now);
    }

    public List<EventAnalysis> searchForEvery(String year){
        return eventAnalysisMapper.searchforevery(year);
    }

    public List<EventAnalysis> searchForEveryDefault(String year){
        return eventAnalysisMapper.searchforeverydefault(year);
    }
}
