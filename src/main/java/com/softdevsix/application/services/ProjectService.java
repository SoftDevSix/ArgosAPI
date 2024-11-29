package com.softdevsix.application.services;

import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectResults;
import com.softdevsix.domain.entities.project.Status;
import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import com.softdevsix.domain.exceptions.ProjectNotFoundException;
import com.softdevsix.domain.repositories.IProjectRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProjectById(UUID projectId) {
        Project project = projectRepository.findById(projectId);
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

        projectRepository.save(project);
    }

    public void calculateProjectRating(UUID projectId) {
        Project project = getProjectById(projectId);
        project.getProjectResults().getCodeAnalysisResult().setActualRating(Rating.A);
        projectRepository.save(project);
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

        projectRepository.save(project);
    }

    public ProjectResults calculateProjectResults(UUID projectId) {
        calculateProjectCoverage(projectId);
        calculateProjectRating(projectId);
        calculateProjectStatus(projectId);

        return getProjectById(projectId).getProjectResults();
    }
}
