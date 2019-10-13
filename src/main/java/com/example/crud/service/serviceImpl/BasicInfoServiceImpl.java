package com.example.crud.service.serviceImpl;

import com.example.crud.bean.*;
import com.example.crud.dao.BasicInfoDao;
import com.example.crud.mapper.BasicInfoMapper;
import com.example.crud.mapper.TunnelBasicInfoMapper;
import com.example.crud.service.BasicInfoService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/2/19  7:10 PM
 */
@Component
public class BasicInfoServiceImpl implements BasicInfoService {
    private BasicInfoDao basicInfoDao;
    @Autowired
    public BasicInfoServiceImpl(BasicInfoDao basicInfoDao){
        this.basicInfoDao = basicInfoDao;
    }
    @Override
    public ResponseMessage selectCities() {
        List<City> list = basicInfoDao.selectCities();
        if (list !=null && list.size()>0){
            return ResponseMessage.<City>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage selectCounty(String city) {
        List<County> list = basicInfoDao.selectCounty(city);
        if (list!=null && list.size()>0){
            return ResponseMessage.<County>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage selectRoadAll(String value2) {
        List<Road> list = basicInfoDao.selectRoadAll(value2);
        if (list!=null && list.size()>0){
            return ResponseMessage.<Road>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage selectBridgeAll(String value2) {
        List<Bridge> list = basicInfoDao.selectBridgeAll(value2);
        if (list!=null && list.size()>0){
            return ResponseMessage.<Bridge>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage selectTunnelAll(String value2) {
        List<Tunel> list = basicInfoDao.selectTunnelAll(value2);
        if (list!=null && list.size()>0){
            return ResponseMessage.<Tunel>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage selectRoadInfo(String value2, String value4) {
        List<Road> list = basicInfoDao.selectRoadInfo(value2,value4);
        if (list!=null && list.size()>0){
            return ResponseMessage.<Road>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage selectBridgeInfo(String value2, String value4) {
        List<Bridge> list = basicInfoDao.selectBridgeInfo(value2,value4);
        if (list!=null && list.size()>0){
            return ResponseMessage.<Bridge>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }

    @Override
    public ResponseMessage selectTunnelInfo(String value2, String value4) {
        List<Tunel> list = basicInfoDao.selectTunnelInfo(value2,value4);
        if (list!=null && list.size()>0){
            return ResponseMessage.<Tunel>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("信息为空").build();
        }
    }
}
