package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  10/2/19  3:42 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Cities implements Serializable {
    private int id;
    private String city;
    private String county;
    private String road;
    private String bridge;
    private String tunnel;
}
