package com.softdevsix.application.services;

import com.softdevsix.application.mappers.requests.ProjectParamsCreateRequest;
import com.softdevsix.application.mappers.requests.ProjectParamsUpdateRequest;
import com.softdevsix.application.mappers.responses.ProjectParamsResponse;
import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import com.softdevsix.domain.repositories.IProjectParamsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectParamsService implements IProjectParamsService {
    private final IProjectParamsRepository iProjectParamsRepository;

    public ProjectParamsService(IProjectParamsRepository iProjectParamsRepository) {
        this.iProjectParamsRepository = iProjectParamsRepository;
    }

    @Override
    public ProjectParamsResponse createProjectParams(ProjectParamsCreateRequest projectParamsCreateRequest) {
        ProjectParams projectParam = new ProjectParams();
        projectParam.setRequiredCoveragePercentage(projectParamsCreateRequest.requiredCoveragePercentage());
        projectParam.setRequiredCodeRating(Rating.valueOf(projectParamsCreateRequest.requiredCodeRating()));

        ProjectParams persistedProjectParam = iProjectParamsRepository.save(projectParam);

        return new ProjectParamsResponse(
                persistedProjectParam.getId(),
                persistedProjectParam.getRequiredCoveragePercentage(),
                persistedProjectParam.getRequiredCodeRating().toString()
        );
    }

    @Override
    public Optional<ProjectParams> getProjectParamsById(UUID id) {
        return iProjectParamsRepository.findById(id);
    }

    @Override
    public Optional<ProjectParams> getProjectParamsByProjectId(UUID projectId) {
        return iProjectParamsRepository.findById(projectId);
    }

    @Override
    public ProjectParamsResponse updateProjectParams(UUID id, ProjectParamsUpdateRequest projectParamsUpdateRequest) {
        Optional<ProjectParams> optionalProjectParams = getProjectParamsById(id);

        if (optionalProjectParams.isEmpty()) {
            throw new RuntimeException("Project params not found");
        }

        ProjectParams projectParam = new ProjectParams();
        projectParam.setRequiredCoveragePercentage(projectParamsUpdateRequest.requiredCoveragePercentage());
        projectParam.setRequiredCodeRating(Rating.valueOf(projectParamsUpdateRequest.requiredCodeRating()));

        ProjectParams persistedProjectParam = iProjectParamsRepository.save(projectParam);

        return new ProjectParamsResponse(
                persistedProjectParam.getId(),
                persistedProjectParam.getRequiredCoveragePercentage(),
                persistedProjectParam.getRequiredCodeRating().toString()
        );
    }
}
