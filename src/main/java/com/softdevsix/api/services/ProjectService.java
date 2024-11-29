package com.softdevsix.api.services;

import com.softdevsix.api.domain.coverage.ProjectCoverageResult;
import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.domain.entities.project.ProjectResults;
import com.softdevsix.api.domain.entities.project.Status;
import com.softdevsix.api.domain.staticanalysis.CodeAnalysisResult;
import com.softdevsix.api.domain.staticanalysis.Rating;
import com.softdevsix.api.exceptions.ProjectNotFoundException;
import com.softdevsix.api.repositories.IProjectRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectService implements IProjectService {
    private final IProjectRepository PROJECTREPOSITORY;

    public ProjectService(IProjectRepository projectRepository) {
        this.PROJECTREPOSITORY = projectRepository;
    }

    public Project getProjectById(UUID projectId) {
        Project project = PROJECTREPOSITORY.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("Project with Id: " + projectId + " not found");
        }
        return project;
    }

    public void calculateProjectCoverage(UUID projectId) {
        Project project = getProjectById(projectId);

        float totalCoverage = 0f;

        for (File file : project.getCoveredFiles()) {
            totalCoverage += file.getCoverageResult().getCoveragePercentage();
        }

        totalCoverage /= project.getCoveredFiles().size();
        project.getProjectResults().getCoverageResult().setTotalCoverage(totalCoverage);

        PROJECTREPOSITORY.save(project);
    }

    public void calculateProjectRating(UUID projectId) {
        Project project = getProjectById(projectId);
        project.getProjectResults().getCodeAnalysisResult().setActualRating(Rating.A);
        PROJECTREPOSITORY.save(project);
    }

    public void calculateProjectStatus(UUID projectId) {
        Project project = getProjectById(projectId);

        ProjectResults projectResults = project.getProjectResults();

        ProjectCoverageResult coverageResult = projectResults.getCoverageResult();
        boolean coveragePassed = coverageResult.getTotalCoverage() >= coverageResult.getRequiredCoverage();

        CodeAnalysisResult analysisResult = projectResults.getCodeAnalysisResult();
        boolean analysisPassed = analysisResult.getActualRating().compareTo(analysisResult.getExpectedRating()) <= 0;

        if (coveragePassed && analysisPassed) {
            projectResults.setStatus(Status.PASSED);
        } else {
            projectResults.setStatus(Status.FAILED);
        }

        PROJECTREPOSITORY.save(project);
    }

    public ProjectResults calculateProjectResults(UUID projectId) {
        calculateProjectCoverage(projectId);
        calculateProjectRating(projectId);
        calculateProjectStatus(projectId);

        return getProjectById(projectId).getProjectResults();
    }
}
