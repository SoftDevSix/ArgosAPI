package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.repositories.IProjectRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface IProjectService {
    Project getProjectById(UUID projectId);
    Project calculateProjectCoverage(Project project);
    Project calculateProjectRating(Project project);
    Project calculateProjectStatus(Project project);
}
