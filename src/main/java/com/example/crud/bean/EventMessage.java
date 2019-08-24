package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author: lcb
 * @Date: 2019  8/18/19  2:52 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EventMessage implements Serializable {
    private int id;
    private String username;
    private String eventdate;
    private String eventtype;
    private String eventresource;
    private String eventdesc;
    private String eventdeal;
    private String eventsure;
}
