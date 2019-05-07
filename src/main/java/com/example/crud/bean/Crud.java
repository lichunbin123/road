package com.example.crud.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Crud implements Serializable {
    private int id;
    private String title;
    private String link;
    private Date date;
    private String author;
    private int tag;
}
