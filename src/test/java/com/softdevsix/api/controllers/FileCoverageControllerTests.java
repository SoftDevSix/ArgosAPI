package com.softdevsix.api.controllers;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.dto.FileCoverageDto;
import com.softdevsix.api.services.IFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileCoverageControllerTests {

    @Mock
    private IFileService fileService;

    @InjectMocks
    private FileCoverageController fileCoverageController;

    private File mockFile;
    private UUID mockFileId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockFileId = UUID.randomUUID();
        mockFile = File.builder()
                .fileId(mockFileId)
                .fileName("TestFile.java")
                .path("/src/test/TestFile.java")
                .lineCode(100)
                .build();
    }

    @Test
    void testGetFileCoverage() {
        float methodCoverage = 80.0f;
        float fileCoverage = 90.0f;
        List<Integer> uncoveredLines = Arrays.asList(10, 20, 30);

        when(fileService.getFileById(mockFileId)).thenReturn(mockFile);
        when(fileService.calculateFileMethodCoverage(mockFile)).thenReturn(methodCoverage);
        when(fileService.calculateFileCoverage(mockFile)).thenReturn(fileCoverage);
        when(fileService.getUncoveredLines(mockFile)).thenReturn(uncoveredLines);

        ResponseEntity<FileCoverageDto> response = fileCoverageController.getFileCoverage(mockFileId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value()); // Usando getStatusCode().value() en lugar de getStatusCodeValue()
        FileCoverageDto dto = response.getBody();
        assertNotNull(dto);
        assertEquals(mockFile.getFileName(), dto.getFileName());
        assertEquals(mockFile.getPath(), dto.getPathFile());
        assertEquals(mockFile.getLineCode(), dto.getLinesCode());
        assertEquals(methodCoverage, dto.getMethodCoverage());
        assertEquals(fileCoverage, dto.getCoveragePercentage());
        assertEquals(uncoveredLines, dto.getUncoveredLines());
    }

    @Test
    void testGetAllFiles() {
        List<File> mockFiles = Arrays.asList(mockFile, File.builder().build());
        when(fileService.getAll()).thenReturn(mockFiles);

        ResponseEntity<List<File>> response = fileCoverageController.getAll();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value()); // Usando getStatusCode().value() en lugar de getStatusCodeValue()
        List<File> files = response.getBody();
        assertNotNull(files);
        assertEquals(mockFiles.size(), files.size());
        assertEquals(mockFiles, files);
    }
}

