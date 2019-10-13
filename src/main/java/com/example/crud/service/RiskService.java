package com.example.crud.service;

import com.example.crud.bean.RiskRecord;
import com.example.crud.utils.ResponseMessage;

/**
 * @author: lcb
 * @Date: 2019  9/27/19  4:33 PM
 */
public interface RiskService {
    ResponseMessage insertRiskRecord(RiskRecord riskRecord);
    ResponseMessage findAllRiskRecord();
    ResponseMessage searchRisk(String input);
}
