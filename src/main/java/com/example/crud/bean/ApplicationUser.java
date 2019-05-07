package com.example.crud.bean;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApplicationUser implements Serializable {
    private int id;
    private String username;
    private String userpassword;
}
