package com.example.crud.mapper;

import com.example.crud.bean.Tunel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/3/19  3:07 PM
 */
@Component
public interface TunnelBasicInfoMapper {
    List<Tunel> selectTunnelAll(String value2);
    List<Tunel> selectTunnelInfo(String value2,String value4);
}
