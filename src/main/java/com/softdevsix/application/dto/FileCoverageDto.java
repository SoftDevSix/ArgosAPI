package com.softdevsix.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FileCoverageDto {
    private String fileName;
    private String pathFile;
    private int linesCode;
    private float methodCoverage;
    private float coveragePercentage;
    private List<Integer> uncoveredLines;
}
