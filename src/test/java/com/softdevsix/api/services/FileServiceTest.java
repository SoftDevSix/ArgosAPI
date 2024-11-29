package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.file.MethodCoverageResult;
import com.softdevsix.api.exceptions.ProjectNotFoundException;
import com.softdevsix.api.repositories.IFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileServiceTest {
    @Mock
    private IFileRepository fileRepository;
    @InjectMocks
    private FileService fileService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFileByIdFileExists() {
        UUID fileId = UUID.randomUUID();
        File file = File.builder()
                .fileId(fileId)
                .fileName("TestFile.java")
                .path("/src/TestFile.java")
                .build();

        when(fileRepository.findById(fileId)).thenReturn(Optional.of(file));

        File result = fileService.getFileById(fileId);

        assertNotNull(result);
        assertEquals(fileId, result.getFileId());
        assertEquals("TestFile.java", result.getFileName());
        verify(fileRepository, times(1)).findById(fileId);
    }

    @Test
    void testGetFileByIdFileNotFound() {
        UUID fileId = UUID.randomUUID();
        when(fileRepository.findById(fileId)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> fileService.getFileById(fileId));
        verify(fileRepository, times(1)).findById(fileId);
    }

    @Test
    void testCalculateFileCoverage() {
        List<MethodCoverageResult> methodCoverages = Arrays.asList(
                MethodCoverageResult.builder()
                        .statements(Map.of(1, true, 2, true, 3, false))
                        .build(),
                MethodCoverageResult.builder()
                        .statements(Map.of(1, true, 2, false, 3, false))
                        .build()
        );

        File file = File.builder()
                .fileName("TestFile.java")
                .methodCoverageResults(methodCoverages)
                .build();

        float coverage = fileService.calculateFileCoverage(file);

        assertEquals(50.0f, coverage, 0.01);
    }

    @Test
    void testCalculateFileCoverageNoMethods() {
        File file = File.builder()
                .methodCoverageResults(Collections.emptyList())
                .build();

        float coverage = fileService.calculateFileCoverage(file);

        assertEquals(0.0f, coverage);
    }

    @Test
    void testCalculateFileMethodCoverage() {
        List<MethodCoverageResult> methodCoverages = Arrays.asList(
                MethodCoverageResult.builder()
                        .statements(Map.of(1, true, 2, true, 3, false))
                        .build(),
                MethodCoverageResult.builder()
                        .statements(Map.of(1, true, 2, false, 3, false))
                        .build()
        );

        File file = File.builder()
                .methodCoverageResults(methodCoverages)
                .build();

        float coverage = fileService.calculateFileMethodCoverage(file);

        assertEquals(50.0f, coverage, 0.01);
    }

    @Test
    void testCalculateFileMethodCoverageNoStatements() {
        File file = File.builder()
                .methodCoverageResults(Collections.emptyList())
                .build();

        float coverage = fileService.calculateFileMethodCoverage(file);

        assertEquals(0.0f, coverage);
    }
}
