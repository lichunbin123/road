package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  9/27/19  3:47 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RiskRecord implements Serializable {
    private int id;
    private String name;
    private String type;
    private String riskfactors;
    private String riskevent;
    private String eventdate;
}
