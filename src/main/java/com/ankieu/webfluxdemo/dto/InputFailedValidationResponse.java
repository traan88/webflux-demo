package com.ankieu.webfluxdemo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InputFailedValidationResponse {

    private int errorCode;
    private String message;
    private int input;
}
