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
     * The status of the pull request coverage (e.g., passed, failed).
     */
    private boolean status;

    /**
     * The overall coverage percentage of the pull request.
     */
    private float coveragePercentage;

    /**
     * The required coverage percentage to pass the pull request's coverage check.
     */
    private float requiredCoveragePercentage;

    /**
     * The code rating of the pull request (e.g., good, average, bad).
     */
    private String codeRating;

    /**
     * The required code rating that the pull request must meet.
     */
    private String requiredCodeRating;

    /**
     * The date and time when the analysis of the pull request coverage was conducted.
     */
    private LocalDateTime analysisDate;

    /**
     * Constructor for PullRequestCoverageDTO.
     *
     * @param pullRequestId The unique identifier of the pull request.
     * @param totalAnalyzedFiles The total number of files analyzed.
     * @param status The status of the pull request coverage.
     * @param coveragePercentage The overall coverage percentage.
     * @param requiredCoveragePercentage The required coverage percentage.
     * @param codeRating The code rating of the pull request.
     * @param requiredCodeRating The required code rating.
     * @param analysisDate The date and time when the analysis was performed.
     */
    public PullRequestCoverageDTO(String pullRequestId, int totalAnalyzedFiles, boolean status, float coveragePercentage, float requiredCoveragePercentage, String codeRating, String requiredCodeRating, LocalDateTime analysisDate) {
        this.pullRequestId = pullRequestId;
        this.totalAnalyzedFiles = totalAnalyzedFiles;
        this.status = status;
        this.coveragePercentage = coveragePercentage;
        this.requiredCoveragePercentage = requiredCoveragePercentage;
        this.codeRating = codeRating;
        this.requiredCodeRating = requiredCodeRating;
        this.analysisDate = analysisDate;
    }
}
