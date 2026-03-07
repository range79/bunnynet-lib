package com.range.bunnynet.core.exception;

public class BunnyInvalidCredentialsException extends BunnyException {

    public BunnyInvalidCredentialsException(String message) {

        super((message == null || message.isEmpty()) ? "Invalid credentials" : message);
    }

    public BunnyInvalidCredentialsException(String message, Throwable throwable) {
        super((message == null || message.isEmpty()) ? "Invalid credentials" : message, throwable);
    }
}
