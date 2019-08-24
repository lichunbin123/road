package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  8/14/19  10:38 AM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Worker implements Serializable {
    private int id;
    private String classname;
    private String name;
    private String job;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;
}
