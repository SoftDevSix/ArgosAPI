package com.softdevsix.api.exception;

/**
 * Exception thrown when there is an error loading JSON data.
 */
public class JsonDataLoadException extends RuntimeException {

    /**
     * Constructor with a message.
     *
     * @param message The error message.
     */
    public JsonDataLoadException(String message) {
        super(message);
    }

    /**
     * Constructor with a message and a cause.
     *
     * @param message The error message.
     * @param cause   The root cause of the error.
     */
    public JsonDataLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
