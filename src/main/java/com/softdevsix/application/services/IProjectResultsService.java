package com.softdevsix.application.services;

import com.softdevsix.domain.entities.project.ProjectResults;

import java.util.Optional;
import java.util.UUID;

public interface IProjectResultsService {
    Optional<ProjectResults> getProjectResultsById(UUID projectId);
    Optional<ProjectResults> calculateProjectResults(UUID projectId);
}
