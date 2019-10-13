package com.example.crud.mapper;

import com.example.crud.bean.EventAnalysis;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lcb
 * @Date: 2019  8/23/19  9:53 PM
 */
@Component
public interface EventAnalysisMapper {
    List<EventAnalysis> searchforyear(String year);
    List<EventAnalysis> searchformonth(String month);
    List<EventAnalysis> searchfordefault(String now);
    List<EventAnalysis> searchforevery(String year);
    List<EventAnalysis> searchforeverydefault(String year);
}
