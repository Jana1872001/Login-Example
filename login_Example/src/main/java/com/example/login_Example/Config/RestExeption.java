package com.example.login_Example.Config;

import com.example.login_Example.Exeptions.AppException;
import com.example.login_Example.dtos.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExeption {

    @ExceptionHandler(value = { AppException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(new ErrorDto(exception.getMessage()));
    }
}