package com.example.exam.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomResponse<T> {
    private T data;
    private String message;
    private int status;

    public CustomResponse(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }
}
