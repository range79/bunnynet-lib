package com.range.bunnynet.core.exception;

public class BunnyFileUploadFailedException extends BunnyException {
    public BunnyFileUploadFailedException(String message) {
        super(message);
    }
    public BunnyFileUploadFailedException(String message,Throwable throwable){
        super(message,throwable);
    }

}
