package com.example.crud.spark;

import lombok.*;

import java.io.Serializable;

/**
 * @author: lcb
 * @Date: 2019  8/9/19  2:34 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DataEntity implements Serializable {
    private String YEAR;
    private String GCSJ;
    private String GCZBS;
    private String GCZBH;
    private String GCZMC;
    private String GCZLX;
    private String SBSFSBM;
    private String GCZLX1;
    private String CDH;
    private String XSFX;
    private String BQ1;
    private String BQ2;
    private String BQ3;
    private String BQ4;
    private String BQ5;
    private String BQ6;
    private String BQ7;
    private String MTC;
    private String TLJ;
}
