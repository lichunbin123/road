package com.example.crud.service.serviceImpl;

import com.example.crud.bean.Plan;
import com.example.crud.bean.RiskRecord;
import com.example.crud.dao.PlanDao;
import com.example.crud.service.PlanService;
import com.example.crud.utils.ResponseMessage;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author: lcb
 * @Date: 2019  10/11/19  1:44 PM
 */
@Component
public class PlanServiceImpl implements PlanService {
    private PlanDao planDao;
    private Plan plan;
    @Autowired
    public PlanServiceImpl(PlanDao planDao){
        this.planDao = planDao;
    }
    @Override
    public ResponseMessage insertPlan(MultipartFile file) {
        Plan plan = new Plan();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());
        if (!file.isEmpty()){
            try {
                String name = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                plan.setName(name);
                plan.setDate(date);
                plan.setContent(bytes);
                planDao.insertPlan(plan);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("文件添加成功").build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("文件为空").build();
        }
    }

    @Override
    public ResponseMessage selectPlanMessage() {
        List<Plan> list = planDao.selectPlanMessage();
        if (list!=null && list.size()>0){
            return ResponseMessage.<Plan>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

//    @Override
//    public ResponseMessage downloadPlan(String name){
////        System.out.println(name);
////        List<Plan> list = planDao.downloadPlan(name);
////        System.out.println(list.get(0));
////        if (list!=null && list.size()>0){
////            return ResponseMessage.<Plan>builder().successStatus(true).responseData(list)
////                    .httpStatus(HttpStatus.OK).build();
////        }else {
////            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
////        }
//    }
}
