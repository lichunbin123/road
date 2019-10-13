package com.example.crud.bean;

import lombok.*;

/**
 * @author: lcb
 * @Date: 2019  10/3/19  10:35 AM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class City {
    private int id;
    private String name;
}
