package com.softdevsix.application.dtos;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.UUID;

@Schema(name = "PullRequestCoverageDTO", description = "DTO for Pull Request Coverage")
public record PullRequestCoverageDTO(
        @Parameter(name = "pullRequestId", description = "The unique identifier of the Pull Request Coverage", example = "123e4567-e89b-12d3-a456-426614174000") UUID pullRequestId,

        @Parameter(name = "totalAnalyzedFiles", description = "The total number of files analyzed", example = "10") int totalAnalyzedFiles,

        @Parameter(name = "totalCoveredFiles", description = "The total number of files covered", example = "8") boolean status,

        @Parameter(name = "coveragePercentage", description = "The coverage percentage", example = "80", required = true) float coveragePercentage,

        @Parameter(name = "requiredCoveragePercentage", description = "The required coverage percentage", example = "80") float requiredCoveragePercentage,

        @Parameter(name = "codeRating", description = "The code rating", example = "A", required = true) String codeRating,

        @Parameter(name = "requiredCodeRating", description = "The required code rating", example = "A") String requiredCodeRating,

        @Parameter(name = "analysisDate", description = "The date of the analysis", example = "2021-01-01T00:00:00Z") Date analysisDate) {
}
