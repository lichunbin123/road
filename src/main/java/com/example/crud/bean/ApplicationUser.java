package com.example.crud.bean;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApplicationUser implements Serializable {
    private int id;
    private String username;
    private String password;
    private String role;
    private Timestamp time;
}
