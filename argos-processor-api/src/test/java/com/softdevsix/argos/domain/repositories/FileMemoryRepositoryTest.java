package com.softdevsix.argos.domain.repositories;

import com.softdevsix.argos.domain.entities.file.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

class FileMemoryRepositoryTest {

    private FileMemoryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FileMemoryRepository();
    }

    @Test
    void testCreateFile() {
        File file = File.builder()
                .fileName("TestFile.java")
                .path("/src/test/TestFile.java")
                .lineCode(50)
                .coverageResult(null)
                .build();

        File createdFile = repository.createFile(file);

        assertNotNull(createdFile.getFileId());
        assertEquals("TestFile.java", createdFile.getFileName());
        assertEquals("/src/test/TestFile.java", createdFile.getPath());
        assertEquals(50, createdFile.getLineCode());
    }

    @Test
    void testUpdateFile() {
        File file = File.builder()
                .fileName("OriginalFile.java")
                .path("/src/original/OriginalFile.java")
                .lineCode(100)
                .coverageResult(null)
                .build();

        File createdFile = repository.createFile(file);
        createdFile.setFileName("UpdatedFile.java");
        createdFile.setPath("/src/updated/UpdatedFile.java");

        File updatedFile = repository.updateFile(createdFile);

        assertNotNull(updatedFile);
        assertEquals("UpdatedFile.java", updatedFile.getFileName());
        assertEquals("/src/updated/UpdatedFile.java", updatedFile.getPath());
    }

    @Test
    void testUpdateNonexistentFile() {
        File nonExistentFile = File.builder()
                .fileId(UUID.randomUUID())
                .fileName("NonExistent.java")
                .path("/src/nonexistent/NonExistent.java")
                .lineCode(30)
                .coverageResult(null)
                .build();

        File result = repository.updateFile(nonExistentFile);

        assertNull(result);
    }

    @Test
    void testFindById() {
        File file = File.builder()
                .fileName("SearchFile.java")
                .path("/src/search/SearchFile.java")
                .lineCode(75)
                .coverageResult(null)
                .build();

        File createdFile = repository.createFile(file);

        File foundFile = repository.findById(createdFile.getFileId());

        assertNotNull(foundFile);
        assertEquals(createdFile.getFileId(), foundFile.getFileId());
    }

    @Test
    void testFindByIdNonexistent() {
        File result = repository.findById(UUID.randomUUID());

        assertNull(result);
    }

    @Test
    void testGetAll() {
        List<File> initialFiles = repository.getAll();
        int initialSize = initialFiles.size();

        File file = File.builder()
                .fileName("ListFile.java")
                .path("/src/list/ListFile.java")
                .lineCode(60)
                .coverageResult(null)
                .build();

        repository.createFile(file);

        List<File> allFiles = repository.getAll();

        assertEquals(initialSize + 1, allFiles.size());
    }

    @Test
    void testDeleteFile() {
        File file = File.builder()
                .fileName("DeleteFile.java")
                .path("/src/delete/DeleteFile.java")
                .lineCode(40)
                .coverageResult(null)
                .build();

        File createdFile = repository.createFile(file);
        boolean deleted = repository.deleteFile(createdFile.getFileId());

        assertTrue(deleted);
        assertNull(repository.findById(createdFile.getFileId()));
    }

    @Test
    void testDeleteNonexistentFile() {
        boolean result = repository.deleteFile(UUID.randomUUID());

        assertFalse(result);
    }
}
