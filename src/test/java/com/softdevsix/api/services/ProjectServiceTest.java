package com.softdevsix.api.services;

import com.softdevsix.api.domain.coverage.ProjectCoverageResult;
import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.file.FileCoverageResult;
import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.domain.entities.project.ProjectParams;
import com.softdevsix.api.domain.entities.project.ProjectResults;
import com.softdevsix.api.domain.entities.project.Status;
import com.softdevsix.api.domain.staticanalysis.CodeAnalysisResult;
import com.softdevsix.api.domain.staticanalysis.Rating;
import com.softdevsix.api.repositories.IProjectRepository;
import com.softdevsix.api.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectServiceTest {
    IProjectRepository projectRepository = new ProjectRepository();;
    private Project buildProject() {
        ProjectParams params = ProjectParams.builder()
                .id(UUID.randomUUID())
                .requiredCoveragePercentage(80f)
                .requiredCodeRating(Rating.B)
                .build();

        Project project = Project.builder()
                .projectId(UUID.randomUUID())
                .projectParams(params)
                .build();

        File file1 = File.builder()
                .fileId(UUID.randomUUID())
                .fileName("Test1.java")
                .path("/src/Test1.java")
                .build();

        File file2 = File.builder()
                .fileId(UUID.randomUUID())
                .fileName("Test2.java")
                .path("/src/Test2.java")
                .build();

        List<File> files = Arrays.asList(file1, file2);

        project.setCoveredFiles(files);

        FileCoverageResult coverageResult1 = FileCoverageResult.builder()
                .id(UUID.randomUUID())
                .coveragePercentage(100f)
                .build();

        FileCoverageResult coverageResult2 = FileCoverageResult.builder()
                .id(UUID.randomUUID())
                .coveragePercentage(0f)
                .build();

        file1.setCoverageResult(coverageResult1);
        file2.setCoverageResult(coverageResult2);

        ProjectCoverageResult coverageResult = ProjectCoverageResult.builder()
                .id(UUID.randomUUID())
                .requiredCoverage(params.getRequiredCoveragePercentage())
                .build();

        CodeAnalysisResult analysisResult = CodeAnalysisResult.builder()
                .id(UUID.randomUUID())
                .expectedRating(params.getRequiredCodeRating())
                .actualRating(Rating.A)
                .build();

        ProjectResults results = ProjectResults.builder()
                .projectId(project.getProjectId())
                .coverageResult(coverageResult)
                .codeAnalysisResult(analysisResult)
                .build();

        project.setProjectResults(results);


        return project;
    }

    @Test
    public void calculateProjectCoverageTest() {
        Project project = buildProject();
        ProjectService projectService = new ProjectService(projectRepository);
        projectRepository.save(project);

        projectService.calculateProjectResults(project.getProjectId());

        Project updatedProject = projectService.getProjectById(project.getProjectId());
        assertEquals(50f, updatedProject.getProjectResults().getCoverageResult().getTotalCoverage());
    }

    @Test
    public void calculateProjectStatus() {
        Project project = buildProject();
        ProjectService projectService = new ProjectService(projectRepository);
        projectRepository.save(project);

        projectService.calculateProjectStatus(project.getProjectId());

        Project updatedProject = projectService.getProjectById(project.getProjectId());
        assertEquals(Status.FAILED, updatedProject.getProjectResults().getStatus());
    }
}
