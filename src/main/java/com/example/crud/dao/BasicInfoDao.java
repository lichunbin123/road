package com.example.crud.dao;

import com.example.crud.bean.*;
import com.example.crud.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/2/19  6:54 PM
 */
@Repository
public class BasicInfoDao {
    private BasicInfoMapper basicInfoMapper;
    private CountyMapper countyMapper;
    private RoadBasicInfoMapper roadBasicInfoMapper;
    private BridgeBasicInfoMapper bridgeBasicInfoMapper;
    private TunnelBasicInfoMapper tunnelBasicInfoMapper;
    @Autowired
    public BasicInfoDao(BasicInfoMapper basicInfoMapper,CountyMapper countyMapper,RoadBasicInfoMapper roadBasicInfoMapper,BridgeBasicInfoMapper bridgeBasicInfoMapper,TunnelBasicInfoMapper tunnelBasicInfoMapper){
        this.basicInfoMapper = basicInfoMapper;
        this.countyMapper = countyMapper;
        this.roadBasicInfoMapper = roadBasicInfoMapper;
        this.bridgeBasicInfoMapper = bridgeBasicInfoMapper;
        this.tunnelBasicInfoMapper = tunnelBasicInfoMapper;
    }

    public List<City> selectCities(){
        return basicInfoMapper.selectCities();
    }

    public List<County> selectCounty(String city){
        return countyMapper.selectCounty(city);
    }

    public List<Road> selectRoadAll(String value2){
        return roadBasicInfoMapper.selectRoadAll(value2);
    }

    public List<Bridge> selectBridgeAll(String value2){
        return bridgeBasicInfoMapper.selectBridgeAll(value2);
    }

    public List<Tunel> selectTunnelAll(String value2){
        return tunnelBasicInfoMapper.selectTunnelAll(value2);
    }

    public List<Road> selectRoadInfo(String value2,String value4){
        return roadBasicInfoMapper.selectRoadInfo(value2,value4);
    }

    public List<Bridge> selectBridgeInfo(String value2,String value4){
        return bridgeBasicInfoMapper.selectBridgeInfo(value2,value4);
    }

    public List<Tunel> selectTunnelInfo(String value2,String value4){
        return tunnelBasicInfoMapper.selectTunnelInfo(value2,value4);
    }
}
