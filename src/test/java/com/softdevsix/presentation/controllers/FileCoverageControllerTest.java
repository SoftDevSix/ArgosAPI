package com.softdevsix.presentation.controllers;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.application.dto.FileCoverageDto;
import com.softdevsix.application.services.File.IFileService;
import com.softdevsix.domain.entities.file.FileCoverageResult;
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
                .coverageResult(FileCoverageResult.builder().build())
                .build();
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

