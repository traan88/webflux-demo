package com.ankieu.webfluxdemo.exception;

public class InputValidationException extends RuntimeException{

    private static final String MSG = "allowed range is 10 -> 20";
    private static final int errorCode = 100;
    private int input;

    public InputValidationException(int input) {
        super(MSG);
        this.input = input;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getInput() {
        return input;
    }
}
