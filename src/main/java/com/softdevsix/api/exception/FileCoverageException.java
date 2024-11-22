package com.softdevsix.api.exception;

/**
 * Custom exception for errors related to file coverage processing.
 */
public class FileCoverageException extends RuntimeException {

    /**
     * Constructor with a message.
     *
     * @param message The error message.
     */
    public FileCoverageException(String message) {
        super(message);
    }

    /**
     * Constructor with a message and cause.
     *
     * @param message The error message.
     * @param cause   The underlying cause of the error.
     */
    public FileCoverageException(String message, Throwable cause) {
        super(message, cause);
    }
}
