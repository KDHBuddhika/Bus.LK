package com.example.Green_X.advicer;

import com.example.Green_X.exception.NotFoundException;
import com.example.Green_X.exception.UserAlreadyExist;
import com.example.Green_X.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AppWideExceptionHandling {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e)
    {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"error",e.getMessage()),
                HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(UserAlreadyExist e)
    {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(409,"error",e.getMessage()),
                HttpStatus.NOT_FOUND);


    }
}
