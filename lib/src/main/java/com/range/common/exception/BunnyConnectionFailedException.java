package com.range.common.exception;

public class BunnyConnectionFailedException extends RuntimeException {
    public BunnyConnectionFailedException(String message) {super(message);}
    public BunnyConnectionFailedException(Throwable throwable){
        super(throwable);
    }
    public BunnyConnectionFailedException(String message,Throwable throwable){
        super(message,throwable);
    }
}
