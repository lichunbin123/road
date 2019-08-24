package com.example.crud.service.serviceImpl;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.bean.ClassMessage;
import com.example.crud.bean.ClassMessageNew;
import com.example.crud.dao.ClassMessageDao;
import com.example.crud.service.ClassService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/11/19  7:45 PM
 */
@Component
public class ClassServiceImpl implements ClassService {
    private ClassMessageNew classMessageNew;
    private ClassMessageDao classMessageDao;
    @Autowired
    public ClassServiceImpl(ClassMessageDao classMessageDao,ClassMessageNew classMessageNew){
        this.classMessageDao = classMessageDao;
        this.classMessageNew = classMessageNew;
    }
    @Override
    public ResponseMessage insertClassMessage(ClassMessage classMessage) {
        String startdate = classMessage.getNewdate()[0];
        String enddate = classMessage.getNewdate()[1];
        System.out.println("classMessage.getNewclassname==="+classMessage.getNewclassname());
        classMessageNew.setNewclassname(classMessage.getNewclassname());
        classMessageNew.setNewclasstype(classMessage.getNewclasstype());
        classMessageNew.setStartdate(startdate);
        classMessageNew.setEnddate(enddate);
        System.out.println("classMessageNew.getNewclassname==="+classMessageNew.getNewclassname());
        classMessageDao.insertClassMessage(classMessageNew);

        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("添加成功").build();
    }

    @Override
    public ResponseMessage deleteClassMessage() {
        return null;
    }

    @Override
    public ResponseMessage findAllClassMessage() {
        List<ClassMessageNew> list = classMessageDao.findAllClassMessage();
        if (list !=null && list.size()>0){
            return ResponseMessage.<ClassMessageNew>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("班次信息为空").build();
        }
    }
}
