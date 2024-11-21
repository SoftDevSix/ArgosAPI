package com.softdevsix.api.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing the coverage details of a file.
 * This DTO is used to transfer file coverage information.
 */
@Getter
@Setter
public class FileCoverageDTO {

    /**
     * The coverage status of the file.
     * True if the file coverage is successful, false otherwise.
     */
    private boolean status;

    /**
     * The total number of lines in the file.
     */
    private int totalLines;

    /**
     * The percentage of code coverage in the file.
     */
    private float coveragePercentage;

    /**
     * A list of issues found in the file during code analysis.
     */
    private List<IssueDTO> issues;

    /**
     * The general coverage percentage for the file.
     */
    private float coverageGeneral;

    /**
     * The line coverage percentage for the file.
     */
    private float lineCoverage;

    /**
     * The method coverage percentage for the file.
     */
    private float methodCoverage;

    /**
     * The class coverage percentage for the file.
     */
    private float classCoverage;
}
