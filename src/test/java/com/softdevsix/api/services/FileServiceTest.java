package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.file.FileCoverageResult;
import com.softdevsix.api.domain.entities.file.MethodCoverageResult;
import com.softdevsix.api.repositories.IFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileServiceTest {

    private FileService fileService;

    @Mock
    private IFileRepository fileRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fileService = new FileService(fileRepository);
    }

    @Test
    void testGetFileById() {
        UUID fileId = UUID.randomUUID();
        File mockFile = File.builder()
                .fileId(fileId)
                .fileName("testFile")
                .build();

        when(fileRepository.findById(fileId)).thenReturn(mockFile);

        File result = fileService.getFileById(fileId);

        assertNotNull(result);
        assertEquals(mockFile, result);
        verify(fileRepository, times(1)).findById(fileId);
    }

    @Test
    void testCalculateFileCoverage() {
        List<MethodCoverageResult> methodCoverages = List.of(
                MethodCoverageResult.builder().statements(Map.of(1, true, 2, false)).build(),
                MethodCoverageResult.builder().statements(Map.of(3, true, 4, true)).build()
        );

        FileCoverageResult coverageResult = FileCoverageResult.builder()
                .allStatements(methodCoverages)
                .build();

        File file = File.builder()
                .coverageResult(coverageResult)
                .build();

        float result = fileService.calculateFileCoverage(file);

        assertEquals(75.0f, result, 0.01);
    }

    @Test
    void testCalculateFileMethodCoverage() {
        List<MethodCoverageResult> methodCoverages = List.of(
                MethodCoverageResult.builder().statements(Map.of(1, true, 2, false)).build(),
                MethodCoverageResult.builder().statements(Map.of(3, true, 4, true, 5, false)).build()
        );

        FileCoverageResult coverageResult = FileCoverageResult.builder()
                .allStatements(methodCoverages)
                .build();

        File file = File.builder()
                .coverageResult(coverageResult)
                .build();

        float result = fileService.calculateFileMethodCoverage(file);

        assertEquals(60.0f, result, 0.01);
    }

    @Test
    void testGetUncoveredLines() {
        List<MethodCoverageResult> methodCoverages = List.of(
                MethodCoverageResult.builder().statements(Map.of(1, true, 2, false)).build(),
                MethodCoverageResult.builder().statements(Map.of(3, false, 4, true)).build()
        );

        FileCoverageResult coverageResult = FileCoverageResult.builder()
                .allStatements(methodCoverages)
                .build();

        File file = File.builder()
                .coverageResult(coverageResult)
                .build();

        List<Integer> result = fileService.getUncoveredLines(file);

        assertNotNull(result);
        assertEquals(List.of(2, 3), result);
    }

    @Test
    void testGetAllFiles() {
        List<File> mockFiles = List.of(
                File.builder().fileName("File1").build(),
                File.builder().fileName("File2").build()
        );

        when(fileRepository.getAll()).thenReturn(mockFiles);

        List<File> result = fileService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(mockFiles, result);
        verify(fileRepository, times(1)).getAll();
    }
}
