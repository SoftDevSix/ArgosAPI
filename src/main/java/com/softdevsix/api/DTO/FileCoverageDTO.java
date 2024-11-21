package com.softdevsix.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) to represent file coverage details.
 */
@Data
@AllArgsConstructor
public class FileCoverageDTO {
    /**
     * Name of the file.
     */
    private String fileName;

    /**
     * Percentage of total coverage in the file.
     */
    private String coveragePercentage;

    /**
     * Path to the file.
     */
    private String pathFile;

    /**
     * Total number of lines of code in the file.
     */
    private int linesCode;

    /**
     * Method coverage percentage in the file.
     */
    private String methodCoverage;
}

