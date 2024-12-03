package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.file.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileMemoryRepositoryTest {

    @Autowired
    private IFileRepository repository;

    @Test
    void testCreateFile() {
        File file = File.builder()
                .fileName("TestFile.java")
                .path("/src/test/TestFile.java")
                .lineCode(50)
                .coverageResult(null)
                .build();

        File createdFile = repository.save(file);

        assertNotNull(createdFile.getFileId());
        assertEquals("TestFile.java", createdFile.getFileName());
        assertEquals("/src/test/TestFile.java", createdFile.getPath());
        assertEquals(50, createdFile.getLineCode());
    }

    @Test
    void testFindById() {
        File file = File.builder()
                .fileName("SearchFile.java")
                .path("/src/search/SearchFile.java")
                .lineCode(75)
                .coverageResult(null)
                .build();

        File createdFile = repository.save(file);
        Optional<File> optionalFile = repository.findById(createdFile.getFileId());
        assertTrue(optionalFile.isPresent());
        assertEquals(createdFile.getFileId(), optionalFile.get().getFileId());
    }

    @Test
    void testFindByIdNonexistent() {
        Optional<File> optionalFile = repository.findById(UUID.randomUUID());
        assertTrue(optionalFile.isEmpty());
    }

    @Test
    void testGetAll() {
        long initialSize = repository.count();
        File file = File.builder()
                .fileName("ListFile.java")
                .path("/src/list/ListFile.java")
                .lineCode(60)
                .coverageResult(null)
                .build();
        repository.save(file);
        assertEquals(initialSize + 1, repository.count());
    }

    @Test
    void testDeleteFile() {
        File file = File.builder()
                .fileName("DeleteFile.java")
                .path("/src/delete/DeleteFile.java")
                .lineCode(40)
                .coverageResult(null)
                .build();

        File createdFile = repository.save(file);
        repository.deleteById(createdFile.getFileId());
        Optional<File> optionalFile = repository.findById(createdFile.getFileId());
        assertTrue(optionalFile.isEmpty());
    }

    @Test
    void testDeleteNonexistentFile() {
        repository.deleteById(UUID.randomUUID());
    }
}
