package com.argos.apirest.exceptions;

/**
 * Custom exception to represent a 404 Not Found error.
 * <p>
 * This exception is used when the requested resource could not be found on the server.
 * The exception includes a message and an HTTP status code of 404 (Not Found).
 * </p>
 */
public class NotFoundException implements IApiException {
    private final String message;
    private final int statusCode;

    /**
     * Constructs a new NotFoundException with the specified detail message.
     * <p>
     * The message typically describes the resource that was not found or provides
     * additional context about the error.
     * </p>
     *
     * @param message the detail message for this exception
     */
    public NotFoundException(String message) {
        this.message = message;
        this.statusCode = 404;
    }

    /**
     * Retrieves the message of the exception.
     * <p>
     * This method returns the detail message that explains the reason for the 404 error,
     * such as the missing resource.
     * </p>
     *
     * @return the message associated with this exception
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the HTTP status code associated with the exception.
     * <p>
     * This method always returns 404, as this exception represents a Not Found error.
     * </p>
     *
     * @return the HTTP status code for this exception (404)
     */
    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
