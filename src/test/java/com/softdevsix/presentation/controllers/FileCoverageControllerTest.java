package com.softdevsix.presentation.controllers;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.application.dto.FileCoverageDto;
import com.softdevsix.application.services.File.IFileService;
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

class FileCoverageControllerTest {

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
        assertEquals(200, response.getStatusCode().value());
        FileCoverageDto dto = response.getBody();
        assertNotNull(dto);
        assertEquals(mockFile.getFileName(), dto.getFileName());
        assertEquals(mockFile.getPath(), dto.getPathFile());
        assertEquals(mockFile.getLineCode(), dto.getLinesCode());
        assertEquals(methodCoverage, dto.getMethodCoverage());
        assertEquals(fileCoverage, dto.getCoveragePercentage());
        assertEquals(uncoveredLines, dto.getUncoveredLines());
    }

}

