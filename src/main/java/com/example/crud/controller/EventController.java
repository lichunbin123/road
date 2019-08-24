package com.example.crud.controller;

import com.example.crud.bean.ClassMessage;
import com.example.crud.bean.EventMessage;
import com.example.crud.bean.InitEventMessage;
import com.example.crud.service.EventService;
import com.example.crud.service.NewEventService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: lcb
 * @Date: 2019  8/19/19  8:32 PM
 */
@Controller
public class EventController {
    private EventService eventService;
    private NewEventService newEventService;
    @Autowired
    public EventController(EventService eventService, NewEventService newEventService){
        this.eventService = eventService;
        this.newEventService = newEventService;
    }
    @RequestMapping("/application/initeventmessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> insertEventMessage(@RequestBody InitEventMessage initEventMessage){
        initEventMessage.setStatus(false);
        ResponseMessage responseMessage = eventService.insertEventMessage(initEventMessage);
       return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/application/searcheventmessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchEventMessage(){
        ResponseMessage responseMessage = eventService.findEventMessage();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/application/neweventmessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> insertNewEventMessage(@RequestBody EventMessage eventMessage){
        System.out.println(eventMessage.getUsername());
        System.out.println(eventMessage.getEventdate());
        System.out.println(eventMessage.getEventtype());
        System.out.println(eventMessage.getEventresource());
        System.out.println(eventMessage.getEventdesc());
        System.out.println(eventMessage.getEventdeal());
        String sure = "审核通过";
        eventMessage.setEventsure(sure);
        ResponseMessage responseMessage = newEventService.insertNewEventMessage(eventMessage);
        newEventService.insertAllEventMessage(eventMessage);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/application/deliniteventmessage/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> delInitEventMessage(@PathVariable int id){
        System.out.println("id==="+id);
        ResponseMessage responseMessage = eventService.deleteEventMessage(id);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/application/cancleneweventmessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> cancleNewEventMessage(@RequestBody EventMessage eventMessage){
        String sure = "审核未通过";
        eventMessage.setEventsure(sure);
        ResponseMessage responseMessage = newEventService.insertNewEventMessage(eventMessage);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/application/searchcountofsuccess")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchEventCountOfSuccess(){
        ResponseMessage responseMessage = newEventService.searchEventCountOfSuccess();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/application/searchcountoffail")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchEventCountOfFail(){
        ResponseMessage responseMessage = newEventService.searchEventCountOfFail();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }
    @RequestMapping("/application/alleventmessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> allRoadEvent(){
        ResponseMessage responseMessage = newEventService.findAllEventMessage();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/application/searchlistofsuccess")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchListOfSuccess(){
        ResponseMessage responseMessage = newEventService.searchListOfSuccess();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/application/searchlistoffail")
    @ResponseBody
    public ResponseEntity<ResponseMessage> searchListOfFail(){
        ResponseMessage responseMessage = newEventService.searchListOfFail();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/application/deletelistofsuccess")
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteListOfSuccess(){
        ResponseMessage responseMessage = newEventService.deleteListOfSuccess();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/application/deletelistoffail")
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteListOfFail(){
        ResponseMessage responseMessage = newEventService.deleteListOfFail();
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/application/eventanalysis/searchformonth/{month}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> analysisForMonth(@PathVariable String month){
        System.out.println("month==="+month);
        ResponseMessage responseMessage = newEventService.searchForMonth(month);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/application/eventanalysis/searchforyear/{year}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> analysisForYear(@PathVariable String year){
        System.out.println("year==="+year);
        ResponseMessage responseMessage = newEventService.searchForYear(year);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

    @RequestMapping("/application/eventanalysis/searchfordefault/{now}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> analysisForDefault(@PathVariable String now){
        System.out.println("now==="+now);
        ResponseMessage responseMessage = newEventService.searchForDefault(now);
        return new ResponseEntity<>(responseMessage,responseMessage.getHttpStatus());
    }

}
