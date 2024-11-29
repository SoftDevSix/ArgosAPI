package com.softdevsix.api.expections;

import com.softdevsix.api.exceptions.ProjectNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectNotFoundExceptionTest {

    @Test
    public void testProjectNotFoundExceptionMessage() {
        String expectedMessage = "Project not found: project123";
        ProjectNotFoundException exception = new ProjectNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }
}
