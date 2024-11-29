package com.softdevsix.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNotFoundExceptionTest {
  @Test
  void testExceptionMessage() {
    String expectedMessage = "Project not found";

    ProjectNotFoundException exception = new ProjectNotFoundException(expectedMessage);

    assertNotNull(exception, "Exception instance should not be null");
    assertEquals(expectedMessage, exception.getMessage(), "Exception message should match the expected message");
  }

  @Test
  void testExceptionInheritance() {
    ProjectNotFoundException exception = new ProjectNotFoundException("Test message");

    assertInstanceOf(RuntimeException.class, exception, "ProjectNotFoundException should inherit from RuntimeException");
  }
}