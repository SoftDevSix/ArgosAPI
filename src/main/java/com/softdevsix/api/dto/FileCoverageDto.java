package com.softdevsix.api.dto;

import lombok.Getter;

@Getter
public class FileCoverageDto {
    private String fileName;
    private String pathFile;
    private int linesCode;
    private float methodCoverage;
    private float coveragePercentage;

    public FileCoverageDto(String fileName, String path, int codeLines, float calculateFileMethodCoverage, float calculateFileCoverage) {
    }
}