package com.example.crud.dao;

import com.example.crud.bean.PublishEvent;
import com.example.crud.mapper.PublishEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/30/19  2:43 PM
 */
@Repository
public class PublishDao {
    private PublishEventMapper publishEventMapper;
    @Autowired
    public PublishDao(PublishEventMapper publishEventMapper){
        this.publishEventMapper = publishEventMapper;
    }

    public void insertPublishEvent(PublishEvent publishEvent){
        publishEventMapper.insertPublishEvent(publishEvent);
    }

    public List<PublishEvent> loadNewsTitle(){
        return publishEventMapper.loadNewsTitle();
    }

    public List<PublishEvent> loadNews(){
        return publishEventMapper.loadNews();
    }

    public List<PublishEvent> loadNewsContent(String title){
        return publishEventMapper.loadNewsContent(title);
    }
}
