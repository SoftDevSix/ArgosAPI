package com.softdevsix.application.services.Project;

import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectResults;

import java.util.UUID;

public interface IProjectService {
    Project getProjectById(UUID projectId);
    void updateProject(Project project);
    ProjectResults getProjectResults(UUID projectId);
    void calculateProjectCoverage(UUID projectId);
    void calculateProjectRating(UUID projectId);
    void calculateProjectStatus(UUID projectId);
}
