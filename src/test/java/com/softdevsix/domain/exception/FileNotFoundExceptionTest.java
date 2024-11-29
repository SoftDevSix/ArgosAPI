package com.softdevsix.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileNotFoundExceptionTest {
  @Test
  void testExceptionMessage() {
    String expectedMessage = "File not found";

    FileNotFoundException exception = new FileNotFoundException(expectedMessage);

    assertNotNull(exception, "Exception instance should not be null");
    assertEquals(expectedMessage, exception.getMessage(), "Exception message should match the expected message");
  }

  @Test
  void testExceptionInheritance() {
    FileNotFoundException exception = new FileNotFoundException("Test message");

    assertInstanceOf(Exception.class, exception, "FileNotFoundException should inherit from Exception");
  }
}