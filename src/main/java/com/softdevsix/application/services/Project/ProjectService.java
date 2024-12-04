package com.softdevsix.application.services.Project;

import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.entities.project.ProjectResults;
import com.softdevsix.domain.entities.project.Status;
import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import com.softdevsix.domain.exceptions.ProjectNotFoundException;
import com.softdevsix.domain.repositories.project.IProjectRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project getProjectById(UUID projectId) {
        Project project = projectRepository.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("Project with Id: " + projectId + " not found");
        }
        return project;
    }

    @Override
    public void updateProject(Project project) {
        UUID projectId = project.getProjectId();
        Project existingProject = projectRepository.findById(projectId);
        if (existingProject == null) {
            throw new ProjectNotFoundException("Project with Id: " + projectId + " not found");
        }
        projectRepository.createProject(project);
    }

    @Override
    public ProjectResults getProjectResults(UUID projectId) {
        Project project = projectRepository.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("Project with Id: " + projectId + " not found");
        }
        return project.getProjectResults();
    }

    @Override
    public void calculateProjectCoverage(UUID projectId) {
        Project project = getProjectById(projectId);

        float totalCoverage = 0f;

        for (File file : project.getCoveredFiles()) {
            totalCoverage += file.getCoverageResult().getCoveragePercentage();
        }

        totalCoverage /= project.getCoveredFiles().size();
        project.getProjectResults().getCoverageResult().setTotalCoverage(totalCoverage);

        projectRepository.update(project);
    }

    @Override
    public void calculateProjectRating(UUID projectId) {
        Project project = getProjectById(projectId);
        project.getProjectResults().getCodeAnalysisResult().setActualRating(Rating.A);

        projectRepository.update(project);
    }

    @Override
    public void calculateProjectStatus(UUID projectId) {
        Project project = getProjectById(projectId);

        ProjectResults projectResults = project.getProjectResults();
        ProjectParams params = project.getProjectParams();

        boolean coverageStatus = getCoverageStatus(params, projectResults);
        boolean codeAnalysisStatus = getCodeAnalysisStatus(params, projectResults);

        if(coverageStatus && codeAnalysisStatus) {
            projectResults.setStatus(Status.PASSED);
        } else {
            projectResults.setStatus(Status.FAILED);
        }

        projectRepository.update(project);
    }

    private boolean getCoverageStatus(ProjectParams params, ProjectResults results) {
        if(params.isProjectCoverage()) {
            ProjectCoverageResult coverageResult = results.getCoverageResult();
            return coverageResult.getTotalCoverage() >= coverageResult.getRequiredCoverage();
        } else {
            return true;
        }
    }

    private boolean getCodeAnalysisStatus(ProjectParams params, ProjectResults results) {
        if(params.isProjectRating()) {
            CodeAnalysisResult analysisResult = results.getCodeAnalysisResult();
            return analysisResult.getActualRating().compareTo(analysisResult.getExpectedRating()) <= 0;
        } else {
            return true;
        }
    }

}
