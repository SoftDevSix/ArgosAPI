package com.softdevsix.api.services;


import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.exceptions.ProjectNotFoundException;
import com.softdevsix.api.mappers.requests.ProjectCreateRequest;
import com.softdevsix.api.mappers.responses.ProjectResponse;

import java.util.UUID;

public interface IProjectService {
    void createProject(Project project);

    Project getProject(UUID projectId) throws ProjectNotFoundException;

    boolean deleteProject(UUID projectId) throws ProjectNotFoundException;

    ProjectResponse addProject(ProjectCreateRequest projectCreateRequest);

    ProjectResponse createProject(ProjectCreateRequest projectCreateRequest);
}
