package com.example.crud.mapper;

import com.example.crud.bean.RiskRecord;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  9/27/19  3:54 PM
 */
@Component
public interface RiskRecordMapper {
    void insertRiskRecord(RiskRecord riskRecord);
    List<RiskRecord> findAllRiskRecord();
    List<RiskRecord> searchRisk(String input);
}
