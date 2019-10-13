package com.example.crud.dao;

import com.example.crud.bean.RiskRecord;
import com.example.crud.mapper.RiskRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  9/27/19  4:21 PM
 */
@Repository
public class RiskRecordDao {
    private RiskRecordMapper riskRecordMapper;
    @Autowired
    public RiskRecordDao(RiskRecordMapper riskRecordMapper){
        this.riskRecordMapper = riskRecordMapper;
    }

    public void insertRiskRecord(RiskRecord riskRecord){
        riskRecordMapper.insertRiskRecord(riskRecord);
    }

    public List<RiskRecord> findAllRiskRecord(){
        return riskRecordMapper.findAllRiskRecord();
    }

    public List<RiskRecord> searchRisk(String input){
        return riskRecordMapper.searchRisk(input);
    }

}
