package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.domain.entities.project.ProjectResults;
import com.softdevsix.api.repositories.IProjectRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface IProjectService {
    Project getProjectById(UUID projectId);
    void calculateProjectCoverage(UUID projectId);
    void calculateProjectRating(UUID projectId);
    void calculateProjectStatus(UUID projectId);
    ProjectResults calculateProjectResults(UUID projectId);
}
