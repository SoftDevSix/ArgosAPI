package com.softdevsix.domain.entity.project;

import com.softdevsix.domain.entity.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entity.staticanalysis.CodeAnalysisResult;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProjectResults {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID projectId;
    private Status status;
    private ProjectCoverageResult coverageResult;
    private CodeAnalysisResult codeAnalysisResult;
}
