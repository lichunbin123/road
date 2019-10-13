package com.example.crud.mapper;

import com.example.crud.bean.Plan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/11/19  12:38 PM
 */
@Component
public interface PlanMapper {
    void insertPlan(Plan plan);
    List<Plan> selectPlanMessage();
    List<Plan> downloadPlan(String name);
}
