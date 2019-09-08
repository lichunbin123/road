package com.example.crud.service;

import com.example.crud.bean.PublishEvent;
import com.example.crud.utils.ResponseMessage;

/**
 * @author: lcb
 * @Date: 2019  8/30/19  2:46 PM
 */
public interface PublishService {
    ResponseMessage insertPublishEvent(PublishEvent publishEvent);
    ResponseMessage loadNewsTitle();
    ResponseMessage loadNews();
    ResponseMessage loadNewsContent(String title);
}
