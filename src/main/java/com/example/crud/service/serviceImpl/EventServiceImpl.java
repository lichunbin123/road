package com.example.crud.service.serviceImpl;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.bean.InitEventMessage;
import com.example.crud.dao.EventDao;
import com.example.crud.service.EventService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import scala.Int;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/19/19  9:44 PM
 */
@Component
public class EventServiceImpl implements EventService {
    private EventDao eventDao;
    @Autowired
    public EventServiceImpl(EventDao eventDao){
        this.eventDao = eventDao;
    }
    @Override
    public ResponseMessage insertEventMessage(InitEventMessage initEventMessage) {
        eventDao.insertEventMessage(initEventMessage);
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("添加成功").build();
    }

    @Override
    public ResponseMessage findEventMessage() {
        List<InitEventMessage> list = eventDao.findEventMessage();
        if (list != null && list.size() > 0){
            return ResponseMessage.<InitEventMessage>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage deleteEventMessage(int id) {
        eventDao.deleteEventMessage(id);
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("删除成功").build();
    }
}
