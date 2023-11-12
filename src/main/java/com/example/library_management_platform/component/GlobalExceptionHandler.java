package com.example.library_management_platform.component;

import com.example.library_management_platform.models.api.response.BaseResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//GlobalExceptionHandler :- Global exception handlers for Controllers.
@ControllerAdvice
public class GlobalExceptionHandler {

    // handleBindException :-  To handle Binding exceptions respond with custom body and message.
    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResponseModel> handleBindException(BindException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseModel(false, errorMessage, null));
    }
}
