package com.example.crud.service;

import com.example.crud.bean.Plan;
import com.example.crud.utils.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: lcb
 * @Date: 2019  10/11/19  1:43 PM
 */
public interface PlanService {
    ResponseMessage insertPlan(MultipartFile file);
    ResponseMessage selectPlanMessage();
//    ResponseMessage downloadPlan(String name);
}
