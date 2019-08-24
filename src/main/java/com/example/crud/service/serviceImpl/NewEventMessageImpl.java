package com.example.crud.service.serviceImpl;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.bean.EventAnalysis;
import com.example.crud.bean.EventMessage;
import com.example.crud.dao.NewEventDao;
import com.example.crud.service.NewEventService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/20/19  2:36 PM
 */
@Component
public class NewEventMessageImpl implements NewEventService {
    private NewEventDao newEventDao;
    @Autowired
    public NewEventMessageImpl(NewEventDao newEventDao){
        this.newEventDao = newEventDao;
    }
    @Override
    public ResponseMessage insertNewEventMessage(EventMessage eventMessage) {
        newEventDao.insertNewEventMessage(eventMessage);
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("添加成功").build();
    }

    @Override
    public ResponseMessage insertAllEventMessage(EventMessage eventMessage) {
        newEventDao.insertAllEventMessage(eventMessage);
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("添加成功").build();
    }

    @Override
    public ResponseMessage searchEventCountOfSuccess() {
        int count = newEventDao.searchEventCountOfSuccess();
        return  ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("查询count成功").count(count).build();
    }

    @Override
    public ResponseMessage searchEventCountOfFail() {
        int count = newEventDao.searchEventCountOfFail();
        return  ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("查询count成功").count(count).build();
    }

    @Override
    public ResponseMessage findAllEventMessage() {
        List<EventMessage> list = newEventDao.findAllEventMessage();
        if (list != null && list.size()>0){
            return ResponseMessage.<EventMessage>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage searchListOfSuccess() {
        List<EventMessage> list = newEventDao.searchListOfSuccess();
        if (list != null && list.size()>0){
            return ResponseMessage.<EventMessage>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage searchListOfFail() {
        List<EventMessage> list = newEventDao.searchListOfFail();
        if (list != null && list.size()>0){
            return ResponseMessage.<EventMessage>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage deleteListOfSuccess() {
        newEventDao.deleteListOfSuccess();
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("删除成功").build();
    }

    @Override
    public ResponseMessage deleteListOfFail() {
        newEventDao.deleteListOfFail();
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("删除成功").build();
    }

    @Override
    public ResponseMessage searchForYear(String year) {
        List<EventAnalysis> list = newEventDao.searchForYear(year);
        if (list !=null && list.size()>0){
            return ResponseMessage.<EventAnalysis>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage searchForMonth(String month) {
        List<EventAnalysis> list = newEventDao.searchForMonth(month);
        if (list !=null && list.size()>0){
            return ResponseMessage.<EventAnalysis>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else{
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage searchForDefault(String now) {
        List<EventAnalysis> list = newEventDao.searchForDefault(now);
        if (list != null && list.size()>0){
            return ResponseMessage.<EventAnalysis>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }
}
