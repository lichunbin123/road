package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: lcb
 * @Date: 2019  8/30/19  10:37 AM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PublishEvent implements Serializable {
    private int id;
    private String classification;
    private String title;
    private String author;
    private String origin;
    private String content;
    private String date;
}
