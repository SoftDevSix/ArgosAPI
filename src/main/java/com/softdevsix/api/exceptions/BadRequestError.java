package com.softdevsix.api.exceptions;

/**
 * Custom exception to represent a 400 Bad Request error.
 * <p>
 * This exception is used when the client sends a request that the server cannot
 * process due to invalid syntax or parameters. The exception includes a message
 * and an HTTP status code of 400 (Bad Request).
 * </p>
 */
public class BadRequestError implements IApiException {
    private final String message;
    private final int statusCode;

    /**
     * Constructs a new BadRequestError with the specified detail message.
     * <p>
     * The message typically provides more details about the cause of the bad request.
     * </p>
     *
     * @param message the detail message for this exception
     */
    public BadRequestError(String message) {
        this.message = message;
        this.statusCode = 400;
    }

    /**
     * Retrieves the message of the exception.
     * <p>
     * This method returns the detail message that explains the cause of the bad request.
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
     * This method always returns 400, as this exception represents a Bad Request error.
     * </p>
     *
     * @return the HTTP status code for this exception (400)
     */
    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
