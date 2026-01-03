package com.range.exception;

public class BunnyFileUploadFailedException extends RuntimeException {
    public BunnyFileUploadFailedException(String message) {
        super(message);
    }
    public BunnyFileUploadFailedException(String message,Throwable throwable){
        super(message,throwable);
    }

}
