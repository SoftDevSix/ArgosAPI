package com.softdevsix.application.services;

import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectResults;
import com.softdevsix.domain.entities.project.Status;
import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import com.softdevsix.domain.exceptions.ProjectNotFoundException;
import com.softdevsix.domain.repositories.IFileRepository;
import com.softdevsix.domain.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;
    private final IFileRepository fileRepository;

    public ProjectService(IProjectRepository projectRepository, @Qualifier("fileMemoryRepository") IFileRepository fileRepository) {
        this.projectRepository = projectRepository;
        this.fileRepository = fileRepository;
    }

    public Project getProjectById(UUID projectId) {
        Project project = projectRepository.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("Project with Id: " + projectId + " not found");
        }
        return project;
    }

    public void calculateProjectCoverage(UUID projectId) {
        System.out.println("project coverage calculation");
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
        System.out.println("project rating calculation");
        Project project = getProjectById(projectId);
        project.getProjectResults().getCodeAnalysisResult().setActualRating(Rating.A);
        projectRepository.save(project);
    }

    public void calculateProjectStatus(UUID projectId) {
        System.out.println("project status calculation");
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
        System.out.println("primero");
        calculateProjectRating(projectId);
        System.out.println("segundo");
        calculateProjectStatus(projectId);
        System.out.println("tercero");

        return getProjectById(projectId).getProjectResults();
    }
}
