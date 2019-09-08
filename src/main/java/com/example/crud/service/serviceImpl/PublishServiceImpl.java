package com.example.crud.service.serviceImpl;

import com.example.crud.bean.EventMessage;
import com.example.crud.bean.PublishEvent;
import com.example.crud.dao.PublishDao;
import com.example.crud.service.PublishService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/30/19  2:47 PM
 */
@Component
public class PublishServiceImpl implements PublishService {
    private PublishDao publishDao;
    @Autowired
    public PublishServiceImpl(PublishDao publishDao){
        this.publishDao = publishDao;
    }
    @Override
    public ResponseMessage insertPublishEvent(PublishEvent publishEvent) {
        publishDao.insertPublishEvent(publishEvent);
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("添加成功").build();
    }

    @Override
    public ResponseMessage loadNewsTitle() {
        List<PublishEvent> list = publishDao.loadNewsTitle();
        if (list!=null&&list.size()>0){
            return ResponseMessage.<PublishEvent>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage loadNews() {
        List<PublishEvent> list = publishDao.loadNews();
        if (list!=null&&list.size()>0){
            return ResponseMessage.<PublishEvent>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage loadNewsContent(String title) {
        List<PublishEvent> list = publishDao.loadNewsContent(title);
        if (list!=null && list.size()>0){
            return ResponseMessage.<PublishEvent>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }
}
