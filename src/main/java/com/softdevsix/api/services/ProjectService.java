package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.exceptions.ProjectNotFoundException;
import com.softdevsix.api.mappers.requests.ProjectCreateRequest;
import com.softdevsix.api.mappers.responses.ProjectResponse;
import com.softdevsix.api.repositories.IProjectRepository;

import java.util.UUID;

public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void createProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project getProject(UUID projectId) throws ProjectNotFoundException {
        Project project = (Project) projectRepository.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("Project with ID " + projectId + " not found.");
        }
        return project;
    }

    @Override
    public boolean deleteProject(UUID projectId) throws ProjectNotFoundException {
        if (projectRepository.findById(projectId) == null) {
            throw new ProjectNotFoundException("Project with ID " + projectId + " not found.");
        }
        projectRepository.delete(projectId);
        return false;
    }

    @Override
    public ProjectResponse addProject(ProjectCreateRequest projectCreateRequest) {
        return null;
    }

    @Override
    public ProjectResponse createProject(ProjectCreateRequest projectCreateRequest) {
        Project project = new Project(projectCreateRequest.getName(), projectCreateRequest.getDescription());
        createProject(project);
        return new ProjectResponse(project.getId(), project.getName(), project.getDescription());
    }
}