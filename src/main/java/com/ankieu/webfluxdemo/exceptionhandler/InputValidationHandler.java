package com.ankieu.webfluxdemo.exceptionhandler;

import com.ankieu.webfluxdemo.dto.InputFailedValidationResponse;
import com.ankieu.webfluxdemo.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex) {
        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());
        response.setInput(ex.getInput());
        return ResponseEntity.badRequest().body(response);
    }
}
