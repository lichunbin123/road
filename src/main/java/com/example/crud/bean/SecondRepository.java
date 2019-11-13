package com.example.crud.bean;

import lombok.*;

/**
 * @author: lcb
 * @Date: 2019  10/10/19  12:26 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SecondRepository {
    private int id;
    private String name;
    private String type;
    private String scope;
    private String place;
    private String head;
    private String phone;
}