package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  10/2/19  6:33 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Road implements Serializable {
    private int id;
    private String name;
    private String county;
    private String level;
    private String lanes;
    private String roadtype;
    private String roadwidth;
    private String greenarea;
}
