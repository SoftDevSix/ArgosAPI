package com.softdevsix.application.services.Rules;

import com.softdevsix.application.dto.ProjectParamsRequestDTO;
import com.softdevsix.application.services.File.IFileService;
import com.softdevsix.application.services.Project.IProjectService;
import com.softdevsix.domain.entities.file.File;
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
    public void executeProject(UUID projectId, ProjectParamsRequestDTO paramsRequestDTO) {
        Project project = PROJECT_SERVICE.getProjectById(projectId);

        if(project == null) throw new BadRequestException("Project cannot be null");

        project.setName(paramsRequestDTO.getProjectName());
        project.setDescription(paramsRequestDTO.getDescription());
        project.getProjectResults().setProjectId(projectId);
        PROJECT_SERVICE.updateProject(project);

        calculateFilesCoverage(project);

        if(paramsRequestDTO.isProjectCoverage()) {
            PROJECT_SERVICE.calculateProjectCoverage(projectId);
        }
        if(paramsRequestDTO.isProjectCoverage()) {
            PROJECT_SERVICE.calculateProjectRating(projectId);
        }

        PROJECT_SERVICE.calculateProjectStatus(projectId);
    }

    private void calculateFilesCoverage(Project project) {
        for(File file : project.getCoveredFiles()) {
            file.getCoverageResult().setCoveragePercentage(FILE_SERVICE.calculateFileCoverage(file));
            file.getCoverageResult().setMethodCoveragePercentage(FILE_SERVICE.calculateFileMethodCoverage(file));
        }

        PROJECT_SERVICE.updateProject(project);
    }
}
