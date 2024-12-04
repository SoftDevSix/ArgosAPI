package com.softdevsix.application.services;

import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;

import java.util.Optional;
import java.util.UUID;

public interface IProjectCoverageResultService {
    Optional<ProjectCoverageResult> getProjectCoverageById(UUID projectId);
    Optional<ProjectCoverageResult> calculateProjectCoverage(UUID projectId);
}
