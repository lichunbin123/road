package com.example.crud.bean;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

/**
 * @author: lcb
 * @Date: 2019  8/11/19  8:58 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Component
public class ClassMessageNew {
    private int id;
    private String newclassname;
    private String newclasstype;
    private String startdate;
    private String enddate;
}
