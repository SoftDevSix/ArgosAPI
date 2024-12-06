package com.softdevsix.application.dto;

import com.softdevsix.domain.entities.project.Status;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ProjectResultDTO {
    Status status;
    CoverageResultDTO coverageResult;
    CodeAnalysisResultDTO codeAnalysisResult;
}
