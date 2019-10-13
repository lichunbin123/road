package com.example.crud.dao;

import com.example.crud.bean.Plan;
import com.example.crud.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/11/19  1:38 PM
 */
@Repository
public class PlanDao {
    private PlanMapper planMapper;
    @Autowired
    public PlanDao(PlanMapper planMapper){
        this.planMapper = planMapper;
    }
    public void insertPlan(Plan plan){
        planMapper.insertPlan(plan);
    }

    public List<Plan> selectPlanMessage(){
        return planMapper.selectPlanMessage();
    }

    public List<Plan> downloadPlan(String name){
        return planMapper.downloadPlan(name);
    }
}
