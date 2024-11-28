package com.softdevsix.api.domain.entities.project;

import com.softdevsix.api.domain.coverage.ProjectCoverageResult;
import com.softdevsix.api.domain.staticanalysis.CodeAnalysisResult;
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
