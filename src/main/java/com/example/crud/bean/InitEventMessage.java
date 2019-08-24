package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author: lcb
 * @Date: 2019  8/19/19  8:07 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InitEventMessage implements Serializable {
    private int id;
    private String username;
    private String eventdate;
    private String eventtype;
    private String eventresource;
    private String eventdesc;
    private String eventdeal;
    private boolean status;
}
