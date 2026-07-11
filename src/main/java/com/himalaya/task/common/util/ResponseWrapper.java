package com.himalaya.task.common.util;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseWrapper <T>{
    private T data;
    private String message;
    private int status;
    private boolean isSuccess;
}
