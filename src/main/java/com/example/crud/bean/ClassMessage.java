package com.example.crud.bean;

import lombok.*;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  8/11/19  5:15 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ClassMessage implements Serializable {
    private int id;
    private String newclassname;
    private String newclasstype;
    private String[] newdate;
}
