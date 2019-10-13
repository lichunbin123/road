package com.example.crud.bean;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: lcb
 * @Date: 2019  10/11/19  12:18 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Plan implements Serializable {
    private int id;
    private String name;
    private String date;
    private byte[] content;
//    private MultipartFile content;
}
