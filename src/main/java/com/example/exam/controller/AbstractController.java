package com.example.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.exam.utils.CustomResponse;

public abstract class AbstractController {

    protected <T> ResponseEntity<CustomResponse<T>> generateResponse(T data, HttpStatus status) {
    	CustomResponse<T> response = new CustomResponse<>(data, "Success", status.value());
        return new ResponseEntity<>(response, status);
    }
}