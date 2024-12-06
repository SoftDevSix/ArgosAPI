package com.softdevsix.application.services;

import com.softdevsix.application.dto.FileCoverageDto;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.domain.entities.file.MethodCoverageResult;
import com.softdevsix.domain.repositories.IFileRepository;
import com.softdevsix.utils.calculate.CoverageProcesor;
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

        FileCoverageResult fileCoverageResult = new FileCoverageResult();
        fileCoverageResult.setMethodCoverageResults(new ArrayList<>());

        File mockFile = File.builder()
                .fileId(fileId)
                .fileName("testFile")
                .fileCoverageResult(fileCoverageResult)
                .build();

        when(fileRepository.findById(fileId)).thenReturn(Optional.of(mockFile));

        FileCoverageDto result = fileService.getFileById(fileId);

        assertNotNull(result);
        assertEquals(mockFile.getFileName(), result.getFileName());
        verify(fileRepository, times(1)).findById(fileId);
    }

    @Test
    void testCalculateFileCoverage() {
        List<MethodCoverageResult> methodCoverages = List.of(
                MethodCoverageResult.builder().methodStatements(Map.of(1, true, 2, false)).build(),
                MethodCoverageResult.builder().methodStatements(Map.of(3, true, 4, true)).build()
        );

        FileCoverageResult coverageResult = FileCoverageResult.builder()
                .methodCoverageResults(methodCoverages)
                .build();

        File file = File.builder()
                .fileCoverageResult(coverageResult)
                .build();

        float result = CoverageProcesor.calculateFileCoverage(file);

        assertEquals(75.0f, result, 0.01);
    }

    @Test
    void testCalculateFileMethodCoverage() {
        List<MethodCoverageResult> methodCoverages = List.of(
                MethodCoverageResult.builder().methodStatements(Map.of(1, true, 2, false)).build(),
                MethodCoverageResult.builder().methodStatements(Map.of(3, true, 4, true, 5, false)).build()
        );

        FileCoverageResult coverageResult = FileCoverageResult.builder()
                .methodCoverageResults(methodCoverages)
                .build();

        File file = File.builder()
                .fileCoverageResult(coverageResult)
                .build();
        float result =  CoverageProcesor.calculateFileMethodCoverage(file);

        assertEquals(60.0f, result, 0.01);
    }

    @Test
    void testGetUncoveredLines() {
        List<MethodCoverageResult> methodCoverages = List.of(
                MethodCoverageResult.builder().methodStatements(Map.of(1, true, 2, false)).build(),
                MethodCoverageResult.builder().methodStatements(Map.of(3, false, 4, true)).build()
        );

        FileCoverageResult coverageResult = FileCoverageResult.builder()
                .methodCoverageResults(methodCoverages)
                .build();

        File file = File.builder()
                .fileCoverageResult(coverageResult)
                .build();

        List<Integer> result = CoverageProcesor.getUncoveredLines(file);

        assertNotNull(result);
        assertEquals(List.of(2, 3), result);
    }

    @Test
    void testGetAllFiles() {
        List<File> mockFiles = List.of(
                File.builder().fileName("File1").build(),
                File.builder().fileName("File2").build()
        );

        when(fileRepository.findAll()).thenReturn(mockFiles);

        List<File> result = fileService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(mockFiles, result);
        verify(fileRepository, times(1)).findAll();
    }
}
