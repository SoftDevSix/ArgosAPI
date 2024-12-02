package com.softdevsix.argos.application.services;

import com.softdevsix.argos.domain.entities.project.Project;
import com.softdevsix.argos.domain.entities.project.ProjectResults;

import java.util.UUID;

public interface IProjectService {
    Project getProjectById(UUID projectId);
    void calculateProjectCoverage(UUID projectId);
    void calculateProjectRating(UUID projectId);
    void calculateProjectStatus(UUID projectId);
    ProjectResults calculateProjectResults(UUID projectId);
}
