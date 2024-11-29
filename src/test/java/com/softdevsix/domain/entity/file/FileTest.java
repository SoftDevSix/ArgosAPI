package com.softdevsix.domain.entity.file;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {
  @Test
  void testFileIdFieldExistsAndIsUUID() {
    File file = new File();

    assertNull(file.getFileId(), "fileId should be null before persistence");

    UUID uuid = UUID.randomUUID();
    file.setFileId(uuid);

    assertEquals(uuid, file.getFileId(), "fileId should match the assigned UUID");
  }

  @Test
  void testGeneratedValueAnnotation() {
    File file = new File();

    UUID generatedUUID = UUID.randomUUID();
    file.setFileId(generatedUUID);

    assertNotNull(file.getFileId(), "fileId should not be null after persistence");
    assertInstanceOf(UUID.class, file.getFileId(), "fileId should be of type UUID");
  }
}
