package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  10/2/19  6:36 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Bridge implements Serializable {
    private int id;
    private String name;
    private String county;
    private String bridgetype;
    private String structuralsystem;
    private String width;
    private String length;
    private String roadtype;
}
