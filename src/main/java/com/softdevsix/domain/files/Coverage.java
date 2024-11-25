package com.softdevsix.domain.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

import com.softdevsix.domain.pullrequests.PullRequest;
import com.softdevsix.domain.types.CoverageStatus;

/**
 * Entity representing the coverage details of a pull request.
 * This entity holds information such as coverage percentage, code ratings, and analysis date,
 * as well as the status of the coverage check.
 */
@Getter
@Setter
@AllArgsConstructor
public class Coverage {

    /**
     * The unique identifier for the coverage record.
     */
    private final UUID coverageId;

    /**
     * The total number of files analyzed in the pull request.
     */
    private final int totalAnalyzedFiles;

    /**
     * The overall coverage percentage of the pull request.
     */
    private final float overallCoveragePercentage;

    /**
     * The required coverage percentage to pass the pull request's coverage check.
     */
    private final float requiredCoveragePercentage;

    /**
     * The code rating of the pull request (e.g., A, B, C, D).
     */
    private final String codeRating;

    /**
     * The required code rating that the pull request must meet.
     */
    private final String requiredRating;

    /**
     * The overall status of the coverage check (e.g., PASSED, FAILED).
     */
    private final CoverageStatus overallStatus;

    /**
     * The date and time when the coverage analysis was performed.
     */
    private final LocalDateTime analysisDate;

    /**
     * The associated pull request for which the coverage is being tracked.
     */
    private PullRequest pullRequest;
}
