package com.softdevsix.application.services;

import com.softdevsix.application.mappers.requests.ProjectParamsCreateRequest;
import com.softdevsix.application.mappers.requests.ProjectParamsUpdateRequest;
import com.softdevsix.application.mappers.responses.ProjectParamsResponse;
import com.softdevsix.domain.entities.project.ProjectParams;

import java.util.Optional;
import java.util.UUID;

public interface IProjectParamsService {
    ProjectParamsResponse createProjectParams(ProjectParamsCreateRequest projectParams);
    Optional<ProjectParams> getProjectParamsById(UUID id);
    Optional<ProjectParams> getProjectParamsByProjectId(UUID projectId);
    ProjectParamsResponse updateProjectParams(UUID id, ProjectParamsUpdateRequest projectParams);
}
