package com.argos.apirest.dtos;

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

    /**
     * Constructor for FileCoverageDTO.
     *
     * @param status The status of the coverage (true if successful, false otherwise).
     * @param totalLines The total number of lines in the file.
     * @param coveragePercentage The percentage of code coverage in the file.
     * @param issues A list of issues detected in the file during the coverage analysis.
     * @param coverageGeneral The general coverage percentage for the file.
     * @param lineCoverage The line coverage percentage for the file.
     * @param methodCoverage The method coverage percentage for the file.
     * @param classCoverage The class coverage percentage for the file.
     */
    public FileCoverageDTO(
            boolean status,
            int totalLines,
            float coveragePercentage,
            List<IssueDTO> issues,
            float coverageGeneral,
            float lineCoverage,
            float methodCoverage,
            float classCoverage
    ) {
        this.status = status;
        this.totalLines = totalLines;
        this.coveragePercentage = coveragePercentage;
        this.issues = issues;
        this.coverageGeneral = coverageGeneral;
        this.lineCoverage = lineCoverage;
        this.methodCoverage = methodCoverage;
        this.classCoverage = classCoverage;
    }
}
