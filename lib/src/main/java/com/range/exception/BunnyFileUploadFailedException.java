package com.range.exception;

public class BunnyFileUploadFailedException extends RuntimeException {
    public BunnyFileUploadFailedException(String message) {
        super(message);
    }
}
