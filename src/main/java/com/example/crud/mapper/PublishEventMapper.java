package com.example.crud.mapper;

import com.example.crud.bean.PublishEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/30/19  10:36 AM
 */
@Component
public interface PublishEventMapper {
    void insertPublishEvent(PublishEvent publishEvent);
    List<PublishEvent> loadNewsTitle();
    List<PublishEvent> loadNews();
    List<PublishEvent> loadNewsContent(String title);
}
