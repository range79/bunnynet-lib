package com.range.common.exception;

public class BunnyInvalidCredentialsException extends RuntimeException {

    public BunnyInvalidCredentialsException(String message) {

        super((message == null || message.isEmpty()) ? "Invalid credentials" : message);
    }

    public BunnyInvalidCredentialsException(String message, Throwable throwable) {
        super((message == null || message.isEmpty()) ? "Invalid credentials" : message, throwable);
    }
}
