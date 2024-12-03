package com.softdevsix.application.services.Rules;

import com.softdevsix.application.dto.ProjectParamsRequestDTO;
import com.softdevsix.application.services.File.IFileService;
import com.softdevsix.application.services.Project.IProjectService;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RulesService implements IRulesService {
    private final IProjectService PROJECT_SERVICE;
    private final IFileService FILE_SERVICE;

    public RulesService(IProjectService projectService, IFileService fileService) {
        this.PROJECT_SERVICE = projectService;
        this.FILE_SERVICE = fileService;
    }

    @Override
    public void saveRules(ProjectParams projectParams, UUID projectId) {
        if(projectParams == null) throw new BadRequestException("Project params cannot be null");

        Project project = PROJECT_SERVICE.getProjectById(projectId);

        project.setProjectParams(projectParams);

        if(projectParams.isProjectCoverage())
            project.getProjectResults().getCoverageResult().setRequiredCoverage(projectParams.getRequiredCoveragePercentage());

        if(projectParams.isProjectRating())
            project.getProjectResults().getCodeAnalysisResult().setExpectedRating(projectParams.getRequiredCodeRating());

        PROJECT_SERVICE.updateProject(project);
    }

    @Override
    public void executeProject(Project project, ProjectParamsRequestDTO paramsRequestDTO) {
        if(project == null) throw new BadRequestException("Project cannot be null");

        if(paramsRequestDTO.isProjectCoverage()) {
            PROJECT_SERVICE.calculateProjectCoverage(project.getProjectId());
        }
        if(paramsRequestDTO.isProjectCoverage()) {
            PROJECT_SERVICE.calculateProjectRating(project.getProjectId());
        }

        PROJECT_SERVICE.calculateProjectStatus(project.getProjectId());
    }
}
