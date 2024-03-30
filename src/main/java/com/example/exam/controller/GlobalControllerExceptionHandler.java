package com.example.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exam.utils.CustomResponse;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomResponse<Object>> handleConnversion(RuntimeException ex) {
    	CustomResponse<Object> response = CustomResponse.builder()
    			.message(ex.getMessage())
    			.status(HttpStatus.BAD_REQUEST.value())
    			.build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}