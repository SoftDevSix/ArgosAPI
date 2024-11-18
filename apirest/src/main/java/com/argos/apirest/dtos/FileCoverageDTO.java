package com.argos.apirest.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing the coverage details of a file.
 * This DTO is used to transfer file coverage information
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
     * The number of lines analyzed in the file.
     */
    private int linesAnalyzed;

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
     * Constructor for FileCoverageDTO.
     *
     * @param status The status of the coverage (true if successful, false otherwise).
     * @param linesAnalyzed The number of lines analyzed in the file.
     * @param totalLines The total number of lines in the file.
     * @param coveragePercentage The percentage of code coverage in the file.
     * @param issues A list of issues detected in the file during the coverage analysis.
     */
    public FileCoverageDTO(boolean status, int linesAnalyzed, int totalLines, float coveragePercentage, List<IssueDTO> issues) {
        this.status = status;
        this.linesAnalyzed = linesAnalyzed;
        this.totalLines = totalLines;
        this.coveragePercentage = coveragePercentage;
        this.issues = issues;
    }
}
