package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  8/23/19  8:00 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EventAnalysis implements Serializable {
    private int id;
    private String eventone;
    private String eventtwo;
    private String eventthree;
    private String eventfour;
}
