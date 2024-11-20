package com.argos.apirest.entities;

import com.argos.apirest.types.CoverageStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing the coverage details of a pull request.
 * This entity holds information such as coverage percentage, code ratings, and analysis date,
 * as well as the status of the coverage check.
 */
@Getter
@Setter
@Entity
public class Coverage {

    /**
     * The unique identifier for the coverage record.
     */
    @Id
    @GeneratedValue
    private UUID coverageId;

    /**
     * The total number of files analyzed in the pull request.
     */
    private int totalAnalyzedFiles;

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
     * The overall status of the coverage check (e.g., PASSED, FAILED).
     */
    @Enumerated(EnumType.STRING)
    private CoverageStatus overallStatus;

    /**
     * The date and time when the coverage analysis was performed.
     */
    @Column(name = "analysis_date", nullable = false)
    private LocalDateTime analysisDate;

    /**
     * The associated pull request for which the coverage is being tracked.
     */
    @ManyToOne
    @JoinColumn(name = "pull_request_id", nullable = false)
    private PullRequest pullRequest;

    /**
     * Constructor for creating a Coverage entity with all required properties.
     *
     * @param coverageId The unique identifier for the coverage record.
     * @param totalAnalyzedFiles The total number of files analyzed.
     * @param overallCoveragePercentage The overall coverage percentage.
     * @param requiredCoveragePercentage The required coverage percentage.
     * @param codeRating The code rating of the pull request.
     * @param requiredRating The required code rating.
     * @param overallStatus The overall status of the coverage check.
     * @param analysisDate The date and time when the coverage analysis was performed.
     * @param pullRequest The associated pull request for which the coverage is tracked.
     */
    public Coverage(UUID coverageId, int totalAnalyzedFiles, float overallCoveragePercentage, float requiredCoveragePercentage,
                    String codeRating, String requiredRating, CoverageStatus overallStatus, LocalDateTime analysisDate, PullRequest pullRequest) {
        this.coverageId = coverageId;
        this.totalAnalyzedFiles = totalAnalyzedFiles;
        this.overallCoveragePercentage = overallCoveragePercentage;
        this.requiredCoveragePercentage = requiredCoveragePercentage;
        this.codeRating = codeRating;
        this.requiredRating = requiredRating;
        this.overallStatus = overallStatus;
        this.analysisDate = analysisDate;
        this.pullRequest = pullRequest;
    }

    /**
     * Default constructor for Coverage entity.
     */
    public Coverage() {}
}
