package com.example.crud.controller;

import com.example.crud.bean.InitEventMessage;
import com.example.crud.bean.PublishEvent;
import com.example.crud.service.PublishService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;

/**
 * @author: lcb
 * @Date: 2019  8/30/19  2:37 PM
 */
@Controller
public class PublishController {
    private PublishService publishService;
    @Autowired
    public PublishController(PublishService publishService){
        this.publishService = publishService;
    }
    @RequestMapping("/publish/edit")
    @ResponseBody
    public ResponseEntity<ResponseMessage> insertPublishEvent(@RequestBody PublishEvent publishEvent){
        Calendar cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        String date = "["+month+"-"+day+"]";
        System.out.println("date="+date);
        publishEvent.setDate(date);

        ResponseMessage responseMessage = publishService.insertPublishEvent(publishEvent);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/publish/loadnewstitle")
    @ResponseBody
    public ResponseEntity<ResponseMessage> loadNewsTitle(){
        ResponseMessage responseMessage = publishService.loadNewsTitle();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/publish/loadnews")
    @ResponseBody
    public ResponseEntity<ResponseMessage> loadNews(){
        ResponseMessage responseMessage = publishService.loadNews();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/publish/loadnewscontent/{title}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> loadNewsContent(@PathVariable String title){
        System.out.println("title==="+title);
        ResponseMessage responseMessage = publishService.loadNewsContent(title);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
}
