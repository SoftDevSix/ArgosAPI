package com.softdevsix.api.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileNotFoundExceptionTests {

    @Test
    void testFileNotFoundExceptionMessage() {
        String message = "File not found: testFile.java";

        FileNotFoundException exception = new FileNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }
}
