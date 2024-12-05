package com.softdevsix.application.mappers.responses;

import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ProjectResultsResponse {
    private String status;
    private ProjectCoverageResult coverageResult;
    private CodeAnalysisResult codeAnalysisResult;
}
