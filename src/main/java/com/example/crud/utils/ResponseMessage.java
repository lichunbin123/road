package com.example.crud.utils;


import com.example.crud.bean.ApplicationUser;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 *
 * @param <T>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponseMessage<T> {

    /**
     * 告知查询成功与否
     */
    private Boolean successStatus;
    /**
     * 如失败，则告知失败原因
     */
    private String messageContent;


    private HttpStatus httpStatus;


    /**
     * 返回数据
     */
    private List<T> responseData;

    /**
     * 返回一个用户数据
     */
    private ApplicationUser responseUser;
}
