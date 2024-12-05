package com.softdevsix.presentation.controllers;

import com.softdevsix.application.services.Project.IProjectService;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.application.dto.FileCoverageDto;
import com.softdevsix.application.services.File.IFileService;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.domain.entities.file.MethodCoverageResult;
import com.softdevsix.domain.entities.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileCoverageControllerTest {

    @Mock
    private IFileService fileService;
    @Mock
    private IProjectService projectService;

    @InjectMocks
    private FileCoverageController fileCoverageController;

    private File mockFile;
    private Project mockProject;
    private UUID mockFileId;
    private UUID mockProjectId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockFileId = UUID.randomUUID();

        mockProjectId = UUID.randomUUID();

        Map<Integer, Boolean> methodStatements = new HashMap<>();
        methodStatements.put(1, true);
        methodStatements.put(2, true);
        methodStatements.put(3, false);
        methodStatements.put(4, true);
        methodStatements.put(5, true);
        methodStatements.put(6, true);
        methodStatements.put(7, true);
        methodStatements.put(8, true);
        methodStatements.put(9, true);
        methodStatements.put(10, true);

        MethodCoverageResult methodCoverage = MethodCoverageResult.builder()
                .statements(methodStatements)
                .build();

        FileCoverageResult coverageResult = FileCoverageResult.builder()
                .allStatements(Arrays.asList(methodCoverage))
                .coveragePercentage(90.0f)
                .methodCoveragePercentage(80.0f)
                .build();

        mockFile = File.builder()
                .fileId(mockFileId)
                .fileName("File.java")
                .path("/src/main/File.java")
                .lineCode(100)
                .coverageResult(coverageResult)
                .build();

        mockProject = Project.builder()
                .projectId(mockProjectId)
                .name("Argos")
                .description("descripcion")
                .coveredFiles(Arrays.asList(mockFile))
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
        when(projectService.getProjectById(mockProject.getProjectId())).thenReturn(mockProject);

        ResponseEntity<FileCoverageDto> response = fileCoverageController.getFileCoverage(mockProject.getProjectId(), "/src/main/File.java");

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

