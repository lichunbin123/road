package com.example.crud.mapper;

import com.example.crud.bean.ClassMessageNew;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/11/19  7:30 PM
 */
@Component
public interface ClassMessageMapper {
    void insertClassMessage(ClassMessageNew classMessageNew);
    void deleteClassMessage();
    List<ClassMessageNew> findAllClassMessage();
}
