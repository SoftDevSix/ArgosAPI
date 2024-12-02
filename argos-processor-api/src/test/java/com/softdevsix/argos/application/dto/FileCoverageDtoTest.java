package com.softdevsix.argos.application.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileCoverageDtoTest {

    @Test
    void testFileCoverageDtoCreation() {
        String fileName = "TestFile.java";
        String pathFile = "/src/files/TestFile.java";
        int linesCode = 200;
        float methodCoverage = 85.5f;
        float coveragePercentage = 78.2f;
        List<Integer> uncoveredLines = List.of(12, 45, 78);

        FileCoverageDto dto = new FileCoverageDto(fileName, pathFile, linesCode, methodCoverage, coveragePercentage, uncoveredLines);

        assertNotNull(dto);
        assertEquals(fileName, dto.getFileName());
        assertEquals(pathFile, dto.getPathFile());
        assertEquals(linesCode, dto.getLinesCode());
        assertEquals(methodCoverage, dto.getMethodCoverage(), 0.01);
        assertEquals(coveragePercentage, dto.getCoveragePercentage(), 0.01);
        assertEquals(uncoveredLines, dto.getUncoveredLines());
    }

    @Test
    void testFileCoverageDtoEmptyUncoveredLines() {
        String fileName = "AnotherFile.java";
        String pathFile = "/src/files/AnotherFile.java";
        int linesCode = 150;
        float methodCoverage = 95.0f;
        float coveragePercentage = 90.0f;
        List<Integer> uncoveredLines = List.of(); // No uncovered lines

        FileCoverageDto dto = new FileCoverageDto(fileName, pathFile, linesCode, methodCoverage, coveragePercentage, uncoveredLines);

        assertNotNull(dto);
        assertEquals(fileName, dto.getFileName());
        assertEquals(pathFile, dto.getPathFile());
        assertEquals(linesCode, dto.getLinesCode());
        assertEquals(methodCoverage, dto.getMethodCoverage(), 0.01);
        assertEquals(coveragePercentage, dto.getCoveragePercentage(), 0.01);
        assertTrue(dto.getUncoveredLines().isEmpty());
    }
}
