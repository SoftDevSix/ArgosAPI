package com.softdevsix.application.dtos;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Schema(name = "PullRequestCoverageDTO", description = "DTO for Pull Request Coverage")
public class PullRequestCoverageDTO {
    @Parameter(name = "id", description = "The unique identifier of the Pull Request Coverage", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
    private final UUID pullRequestId;

    @Parameter(name = "totalAnalyzedFiles", description = "The total number of files analyzed", example = "10", required = true)
    private final int totalAnalyzedFiles;


    @Parameter(name = "totalCoveredFiles", description = "The total number of files covered", example = "8", required = true)
    private final boolean status;

    @Parameter(name = "coveragePercentage", description = "The coverage percentage", example = "80", required = true)
    private final float coveragePercentage;

    @Parameter(name = "requiredCoveragePercentage", description = "The required coverage percentage", example = "80", required = true)
    private final float requiredCoveragePercentage;

    @Parameter(name = "codeRating", description = "The code rating", example = "A", required = true)
    private final String codeRating;

    @Parameter(name = "requiredCodeRating", description = "The required code rating", example = "A", required = true)
    private final String requiredCodeRating;

    @Parameter(name = "analysisDate", description = "The date of the analysis", example = "2021-01-01T00:00:00Z", required = true)
    private final Date analysisDate;

    public PullRequestCoverageDTO(UUID pullRequestId, int totalAnalyzedFiles, boolean status,
                                  float coveragePercentage, float requiredCoveragePercentage,
                                  String codeRating, String requiredCodeRating, Date analysisDate) {
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
