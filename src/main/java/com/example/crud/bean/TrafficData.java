package com.example.crud.bean;

import lombok.*;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author: lcb
 * @Date: 2019  8/5/19  5:34 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TrafficData implements Serializable{
    private String GCSJ;
    private String GCZMC;
    private int BQ1;
    private int BQ2;
    private int BQ3;
    private int BQ4;
    private int BQ5;
    private int BQ6;
    private int BQ7;
    private int MTC;
    private int TLJ;
    private double total;
}
