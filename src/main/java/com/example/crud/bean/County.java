package com.example.crud.bean;

import lombok.*;

/**
 * @author: lcb
 * @Date: 2019  10/3/19  10:36 AM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class County {
    private int id;
    private String city;
    private String county;
}
