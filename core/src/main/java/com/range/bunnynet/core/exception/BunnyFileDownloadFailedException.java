package com.range.bunnynet.core.exception;
public class BunnyFileDownloadFailedException extends RuntimeException {
    public BunnyFileDownloadFailedException(String message) { super(message); }
    public BunnyFileDownloadFailedException(String message, Throwable cause) { super(message, cause); }
}
