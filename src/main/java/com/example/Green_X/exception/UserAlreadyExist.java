package com.example.Green_X.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class UserAlreadyExist extends RuntimeException {

    public UserAlreadyExist (String message){
        super(message);
    }
}
