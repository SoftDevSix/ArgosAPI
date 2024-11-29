package com.softdevsix.domain.exceptions.client;

public class FileManagerException extends RuntimeException {
    public FileManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}