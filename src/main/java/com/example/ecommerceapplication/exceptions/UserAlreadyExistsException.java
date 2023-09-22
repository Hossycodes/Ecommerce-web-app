package com.example.ecommerceapplication.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RuntimeException{
    private String message;

    private HttpStatus status;


    public UserAlreadyExistsException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
