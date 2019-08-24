package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  8/19/19  8:12 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AfterEventMessage implements Serializable {
    private String name;
    private String date1;
    private String date2;
    private String type;
    private String resource;
    private String desc;
    private String deal;
}
