package com.range.common.exception;
public class BunnyFileDownloadFailedException extends RuntimeException {
    public BunnyFileDownloadFailedException(String message) { super(message); }
    public BunnyFileDownloadFailedException(String message, Throwable cause) { super(message, cause); }
}
