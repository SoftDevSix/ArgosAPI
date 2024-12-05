package com.softdevsix.application.services.rules;

import com.softdevsix.application.dto.ProjectParamsRequestDTO;
import com.softdevsix.application.services.file.IFileService;
import com.softdevsix.application.services.project.IProjectService;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RulesService implements IRulesService {
    private final IProjectService projectService;
    private final IFileService fileService;

    public RulesService(IProjectService projectService, IFileService fileService) {
        this.projectService = projectService;
        this.fileService = fileService;
    }

    @Override
    public void saveRules(ProjectParams projectParams, UUID projectId) {
        if(projectParams == null) throw new BadRequestException("Project params cannot be null");

        Project project = projectService.getProjectById(projectId);

        project.setProjectParams(projectParams);

        if(projectParams.isProjectCoverage())
            project.getProjectResults().getCoverageResult().setRequiredCoverage(projectParams.getRequiredCoveragePercentage());

        if(projectParams.isProjectRating())
            project.getProjectResults().getCodeAnalysisResult().setExpectedRating(projectParams.getRequiredCodeRating());

        projectService.updateProject(project);
    }

    @Override
    public void executeProject(UUID projectId, ProjectParamsRequestDTO paramsRequestDTO) {
        Project project = projectService.getProjectById(projectId);

        if(project == null) throw new BadRequestException("Project cannot be null");

        project.setName(paramsRequestDTO.getProjectName());
        project.setDescription(paramsRequestDTO.getDescription());
        project.getProjectResults().setProjectId(projectId);
        projectService.updateProject(project);

        calculateFilesCoverage(project);

        if(paramsRequestDTO.isProjectCoverage()) {
            projectService.calculateProjectCoverage(projectId);
        }
        if(paramsRequestDTO.isProjectCoverage()) {
            projectService.calculateProjectRating(projectId);
        }

        projectService.calculateProjectStatus(projectId);
    }

    private void calculateFilesCoverage(Project project) {
        for(File file : project.getCoveredFiles()) {
            file.getCoverageResult().setCoveragePercentage(fileService.calculateFileCoverage(file));
            file.getCoverageResult().setMethodCoveragePercentage(fileService.calculateFileMethodCoverage(file));
        }

        projectService.updateProject(project);
    }
}
