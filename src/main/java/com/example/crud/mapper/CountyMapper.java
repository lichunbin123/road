package com.example.crud.mapper;

import com.example.crud.bean.County;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  10/3/19  11:33 AM
 */
@Component
public interface CountyMapper {
    List<County> selectCounty(String city);
}
