package com.example.crud.mapper;

import com.example.crud.bean.Bridge;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/3/19  2:57 PM
 */
@Component
public interface BridgeBasicInfoMapper {
    List<Bridge> selectBridgeAll(String value2);
    List<Bridge> selectBridgeInfo(String value2,String value4);
}
