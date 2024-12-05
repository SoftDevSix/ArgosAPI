package com.softdevsix.application.services;

import com.softdevsix.application.mappers.requests.ProjectCreateRequest;
import com.softdevsix.application.mappers.requests.ProjectUpdateRequest;
import com.softdevsix.application.mappers.responses.ProjectResponse;
import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectResults;
import com.softdevsix.domain.entities.project.Status;
import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import com.softdevsix.domain.exceptions.FileNotFoundException;
import com.softdevsix.domain.repositories.IProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjectService implements IProjectService {
    IProjectRepository projectRepository;

    @Override
    public Project getProjectById(UUID projectId) {
        Optional<Project> projectOptional = Optional.empty();
        for (Project dbProject : projectRepository.findAll()) {
            if (dbProject.getProjectId().equals(projectId)){
                projectOptional =  Optional.of(dbProject);
                break;
            }
        }
        if (projectOptional.isEmpty()){
            throw new FileNotFoundException("Project not found");
        }

        return projectOptional.get();
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

    @Override
    public ProjectResults calculateProjectResults(UUID projectId) {
        calculateProjectCoverage(projectId);
        calculateProjectRating(projectId);
        calculateProjectStatus(projectId);

        return getProjectById(projectId).getProjectResults();
    }

    public void calculateProjectCoverage(UUID projectId) {
        Project project = getProjectById(projectId);

        float totalCoverage = 0f;

        for (File file : project.getFiles()) {
            totalCoverage += file.getFileCoverageResult().getCoveragePercentage();
        }

        totalCoverage /= project.getFiles().size();
        project.getProjectResults().getCoverageResult().setTotalCoverage(totalCoverage);

        projectRepository.save(project);
    }

    public void calculateProjectRating(UUID projectId) {
        Project project = getProjectById(projectId);
        project.getProjectResults().getCodeAnalysisResult().setActualRating((Rating.A).name());
        projectRepository.save(project);
    }

    public void calculateProjectStatus(UUID projectId) {
        Project project = getProjectById(projectId);

        ProjectResults projectResults = project.getProjectResults();

        ProjectCoverageResult coverageResult = projectResults.getCoverageResult();
        boolean coveragePassed = coverageResult.getTotalCoverage() >= coverageResult.getTotalCoverage();

        CodeAnalysisResult analysisResult = projectResults.getCodeAnalysisResult();
        boolean analysisPassed = analysisResult.getActualRating().compareTo(analysisResult.getActualRating()) <= 0;

        if (coveragePassed && analysisPassed) {
            projectResults.setStatus((Status.PASSED).name());
        } else {
            projectResults.setStatus((Status.FAILED).name());
        }

        projectRepository.save(project);
    }


}
