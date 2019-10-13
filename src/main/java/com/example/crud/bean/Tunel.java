package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  10/2/19  6:38 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Tunel implements Serializable {
    private int id;
    private String name;
    private String county;
    private String roadtype;
    private String length;
    private String width;
    private String lanes;
    private String height;
    private String rocktype;
}
