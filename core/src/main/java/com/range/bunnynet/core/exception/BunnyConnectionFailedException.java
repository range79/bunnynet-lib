package com.range.bunnynet.core.exception;

import java.io.IOException;

public class BunnyConnectionFailedException extends BunnyException {
    public BunnyConnectionFailedException(String message) {super(message);}
    public BunnyConnectionFailedException(Throwable throwable){
        super(throwable);
    }
    public BunnyConnectionFailedException(String message,Throwable throwable){
        super(message,throwable);
    }
}
