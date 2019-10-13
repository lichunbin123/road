package com.example.crud.mapper;

import com.example.crud.bean.Cities;
import com.example.crud.bean.City;
import com.example.crud.bean.County;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/2/19  3:41 PM
 */
@Component
public interface BasicInfoMapper {
    List<City> selectCities();
}
