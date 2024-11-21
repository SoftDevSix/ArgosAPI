package com.softdevsix.api;

import com.softdevsix.api.exceptions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionsTest {

    @Test
    void testBadRequestError() {
        String message = "Invalid request syntax";
        BadRequestError badRequestError = new BadRequestError(message);

        assertEquals(message, badRequestError.getMessage());
        assertEquals(400, badRequestError.getStatusCode());
    }

    @Test
    void testNotFoundError() {
        String message = "Resource not found";
        NotFoundError notFoundError = new NotFoundError(message);

        assertEquals(message, notFoundError.getMessage());
        assertEquals(404, notFoundError.getStatusCode());
    }

    @Test
    void testIApiExceptionImplementation() {
        IApiException badRequestError = new BadRequestError("Bad request");
        IApiException notFoundError = new NotFoundError("Not found");

        assertInstanceOf(IApiException.class, badRequestError);
        assertInstanceOf(IApiException.class, notFoundError);
    }
}

