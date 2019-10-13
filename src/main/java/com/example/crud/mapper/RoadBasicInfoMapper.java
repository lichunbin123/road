package com.example.crud.mapper;

import com.example.crud.bean.Road;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/3/19  2:43 PM
 */
@Component
public interface RoadBasicInfoMapper {
    List<Road> selectRoadAll(String value2);
    List<Road> selectRoadInfo(String value2,String value4);
}
