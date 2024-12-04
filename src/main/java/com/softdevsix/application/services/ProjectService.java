package com.softdevsix.application.services;

import com.softdevsix.application.mappers.requests.ProjectCreateRequest;
import com.softdevsix.application.mappers.requests.ProjectUpdateRequest;
import com.softdevsix.application.mappers.responses.ProjectResponse;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.exceptions.ProjectNotFoundException;
import com.softdevsix.domain.repositories.IProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> getProjectById(UUID projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (projectOptional.isEmpty()) {
            throw new ProjectNotFoundException("Project with Id: " + projectId + " not found");
        }

        return projectOptional;
    }

    @Override
    public ProjectResponse createProject(ProjectCreateRequest projectCreateRequest) {
        // TODO: Review repository on GitHub
        return null;
    }

    @Override
    public ProjectResponse updateProject(UUID projectId, ProjectUpdateRequest projectUpdateRequest) {
        // TODO: Review repository on GitHub
        return null;
    }

//    @Override
//    public Optional<ProjectResults> calculateProjectResults(UUID projectId) {
//        calculateProjectCoverage(projectId);
//        calculateProjectRating(projectId);
//        calculateProjectStatus(projectId);
//
//        return getProjectById(projectId).getProjectResults();
//    }
}
