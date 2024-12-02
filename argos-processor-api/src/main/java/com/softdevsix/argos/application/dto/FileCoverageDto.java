package com.softdevsix.argos.application.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FileCoverageDto {
    private String fileName;
    private String pathFile;
    private int linesCode;
    private float methodCoverage;
    private float coveragePercentage;
    private List<Integer> uncoveredLines;

    public FileCoverageDto(String fileName, String pathFile, int linesCode, float methodCoverage, float coveragePercentage, List<Integer> uncoveredLines) {
        this.fileName = fileName;
        this.pathFile = pathFile;
        this.linesCode = linesCode;
        this.methodCoverage = methodCoverage;
        this.coveragePercentage = coveragePercentage;
        this.uncoveredLines = uncoveredLines;
    }

}
