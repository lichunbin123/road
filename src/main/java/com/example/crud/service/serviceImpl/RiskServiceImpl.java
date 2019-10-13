package com.example.crud.service.serviceImpl;

import com.example.crud.bean.PublishEvent;
import com.example.crud.bean.RiskRecord;
import com.example.crud.dao.RiskRecordDao;
import com.example.crud.service.RiskService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  9/27/19  4:35 PM
 */
@Component
public class RiskServiceImpl implements RiskService {
    private RiskRecordDao riskRecordDao;
    @Autowired
    public RiskServiceImpl(RiskRecordDao riskRecordDao){
        this.riskRecordDao = riskRecordDao;
    }
    @Override
    public ResponseMessage insertRiskRecord(RiskRecord riskRecord) {
        riskRecordDao.insertRiskRecord(riskRecord);
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("添加成功").build();
    }

    @Override
    public ResponseMessage findAllRiskRecord() {
        List<RiskRecord> list = riskRecordDao.findAllRiskRecord();
        if (list!=null&&list.size()>0){
            return ResponseMessage.<RiskRecord>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage searchRisk(String input) {
        List<RiskRecord> list = riskRecordDao.searchRisk(input);
        if (list!=null&&list.size()>0){
            return ResponseMessage.<RiskRecord>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }
}
