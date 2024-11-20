package com.argos.apirest.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing coverage information related to a pull request.
 * This DTO contains details such as the pull request ID, coverage percentages, code ratings,
 * and the date when the analysis was performed.
 */
@Getter
@Setter
public class PullRequestCoverageDTO {

    /**
     * The unique identifier of the pull request.
     */
    private String pullRequestId;

    /**
     * The total number of files analyzed in the pull request.
     */
    private int totalAnalyzedFiles;

    /**
     * The overall status of the pull request coverage (e.g., Passed, Failed).
     */
    private String overallStatus;

    /**
     * The overall coverage percentage of the pull request.
     */
    private float overallCoveragePercentage;

    /**
     * The required coverage percentage to pass the pull request's coverage check.
     */
    private float requiredCoveragePercentage;

    /**
     * The code rating of the pull request (e.g., A, B, C, D).
     */
    private String codeRating;

    /**
     * The required code rating that the pull request must meet.
     */
    private String requiredRating;

    /**
     * The date and time when the analysis of the pull request coverage was conducted.
     */
    private LocalDateTime analysisDate;

    /**
     * Constructor for PullRequestCoverageDTO.
     *
     * @param pullRequestId The unique identifier of the pull request.
     * @param totalAnalyzedFiles The total number of files analyzed.
     * @param overallStatus The overall status of the pull request coverage.
     * @param overallCoveragePercentage The overall coverage percentage.
     * @param requiredCoveragePercentage The required coverage percentage.
     * @param codeRating The code rating of the pull request.
     * @param requiredRating The required code rating.
     * @param analysisDate The date and time when the analysis was performed.
     */
    public PullRequestCoverageDTO(String pullRequestId, int totalAnalyzedFiles, String overallStatus, float overallCoveragePercentage,
                                  float requiredCoveragePercentage, String codeRating, String requiredRating, LocalDateTime analysisDate) {
        this.pullRequestId = pullRequestId;
        this.totalAnalyzedFiles = totalAnalyzedFiles;
        this.overallStatus = overallStatus;
        this.overallCoveragePercentage = overallCoveragePercentage;
        this.requiredCoveragePercentage = requiredCoveragePercentage;
        this.codeRating = codeRating;
        this.requiredRating = requiredRating;
        this.analysisDate = analysisDate;
    }
}
