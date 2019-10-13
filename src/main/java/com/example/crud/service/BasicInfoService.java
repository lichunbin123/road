package com.example.crud.service;

import com.example.crud.utils.ResponseMessage;

/**
 * @author: lcb
 * @Date: 2019  10/2/19  7:09 PM
 */
public interface BasicInfoService {
    ResponseMessage selectCities();
    ResponseMessage selectCounty(String city);
    ResponseMessage selectRoadAll(String value2);
    ResponseMessage selectBridgeAll(String value2);
    ResponseMessage selectTunnelAll(String value2);
    ResponseMessage selectRoadInfo(String value2,String value4);
    ResponseMessage selectBridgeInfo(String value2,String value4);
    ResponseMessage selectTunnelInfo(String value2,String value4);
}
