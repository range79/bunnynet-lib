package com.range.bunnynet.core.exception;

import java.io.IOException;

public class BunnyException extends RuntimeException {
    public BunnyException(String message){
        super(message);
    }
    public BunnyException(String message,Throwable throwable){
        super(message,throwable);
    }
    public BunnyException(Throwable throwable){
        super(throwable);
    }
}
