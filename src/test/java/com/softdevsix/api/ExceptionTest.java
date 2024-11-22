package com.softdevsix.api;

import com.softdevsix.api.exception.FileCoverageException;
import com.softdevsix.api.exception.JsonDataLoadException;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionTest {

    @Test
    void testFileCoverageExceptionWithMessage() {
        FileCoverageException exception = new FileCoverageException("Custom error message");

        assertEquals("Custom error message", exception.getMessage());
    }

    @Test
    void testFileCoverageExceptionWithMessageAndCause() {
        Exception cause = new Exception("Cause");

        FileCoverageException exception = new FileCoverageException("Custom error message", cause);

        assertEquals("Custom error message", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testJsonDataLoadExceptionWithMessage() {
        JsonDataLoadException exception = new JsonDataLoadException("Invalid JSON data");

        assertEquals("Invalid JSON data", exception.getMessage());
    }

    @Test
    void testJsonDataLoadExceptionWithMessageAndCause() {
        IOException cause = new IOException("I/O error");

        JsonDataLoadException exception = new JsonDataLoadException("Invalid JSON data", cause);

        assertEquals("Invalid JSON data", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

}
