package com.range.common.exception;

public class BunnyInvalidCredentialsException extends RuntimeException {
    public BunnyInvalidCredentialsException(String message){
        super(message);
    }
    public BunnyInvalidCredentialsException(String message,Throwable throwable){
        super(message,throwable);
    }
}
