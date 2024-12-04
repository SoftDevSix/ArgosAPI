package com.softdevsix.application.services;

import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.repositories.IProjectCoverageResultRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectCoverageResultService implements IProjectCoverageResultService {
    private final IProjectCoverageResultRepository iProjectCoverageResultRepository;
    private final IProjectService iProjectService;

    public ProjectCoverageResultService(IProjectCoverageResultRepository iProjectCoverageResultRepository,
                                        IProjectService iProjectService) {
        this.iProjectCoverageResultRepository = iProjectCoverageResultRepository;
        this.iProjectService = iProjectService;
    }

    @Override
    public Optional<ProjectCoverageResult> getProjectCoverageById(UUID projectId) {
        Optional<ProjectCoverageResult> projectCoverageResultOptional = iProjectCoverageResultRepository.findById(projectId);

        if (projectCoverageResultOptional.isEmpty()) {
            throw new RuntimeException("Cannot found coverage for project with id: " + projectId);
        }

        return projectCoverageResultOptional;
    }

    @Override
    public Optional<ProjectCoverageResult> calculateProjectCoverage(UUID projectId) {
        Optional<Project> project = iProjectService.getProjectById(projectId);

        float totalCoverage = calculateTotalCoverage(project.get());

        ProjectCoverageResult coverageResult = ProjectCoverageResult.builder()
                .totalCoverage(totalCoverage)
                .build();

        return Optional.of(iProjectCoverageResultRepository.save(coverageResult));
    }

    private float calculateTotalCoverage(Project project) {
        float totalCoverage = 0f;

        for (File file : project.getFiles()) {
            totalCoverage += file.getFileCoverageResult().getCoveragePercentage();
        }

        totalCoverage /= project.getFiles().size();
        project.getProjectResults().getCoverageResult().setTotalCoverage(totalCoverage);

//        projectRepository.save(project.get());
        return 0.0f;
    }
}
