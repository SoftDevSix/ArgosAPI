package com.softdevsix.argos.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectNotFoundExceptionTest {

    @Test
    void testProjectNotFoundExceptionMessage() {
        String expectedMessage = "Project not found: project123";
        ProjectNotFoundException exception = new ProjectNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }
}
