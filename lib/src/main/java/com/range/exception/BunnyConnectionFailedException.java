package com.range.exception;

public class BunnyConnectionFailedException extends RuntimeException {
    public BunnyConnectionFailedException(String message) {super(message);}
    public BunnyConnectionFailedException(Throwable throwable){
        super(throwable);
    }
}
