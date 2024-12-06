package com.softdevsix.application.services;

import com.softdevsix.application.dto.ProjectParamsRequestDTO;
import com.softdevsix.application.services.file.IFileService;
import com.softdevsix.application.services.project.IProjectService;
import com.softdevsix.application.services.rules.IRulesService;
import com.softdevsix.application.services.rules.RulesService;
import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.entities.project.ProjectResults;
import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RulesServiceTest {
    private IProjectService projectService;
    private IFileService fileService;
    private IRulesService rulesService;

    @BeforeEach
    void setUp() {
        projectService = mock(IProjectService.class);
        fileService = mock(IFileService.class);
        rulesService = new RulesService(projectService, fileService);
    }

    private Project buildCompleteProject() {
        ProjectParams params = ProjectParams.builder()
                .id(UUID.randomUUID())
                .projectCoverage(true)
                .requiredCoveragePercentage(80f)
                .projectRating(true)
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
                .coveragePercentage(100.0f)
                .build();

        FileCoverageResult coverageResult2 = FileCoverageResult.builder()
                .id(UUID.randomUUID())
                .coveragePercentage(80.0f)
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

    private Project buildProject() {
        return Project.builder()
                .projectId(UUID.randomUUID())
                .name("test")
                .description("test")
                .build();
    }

    private ProjectParams buildProjectParams() {
        return ProjectParams.builder()
                .id(UUID.randomUUID())
                .requiredCoveragePercentage(80f)
                .requiredCodeRating(Rating.B)
                .build();
    }

    @Test
    void setProjectParamsNotNullTest() {
        Project project = buildProject();

        when(projectService.getProjectById(project.getProjectId())).thenReturn(project);

        ProjectParams params = buildProjectParams();

        rulesService.saveRules(params, project.getProjectId());

        assertNotNull(project.getProjectParams());
    }

    @Test
    void projectParamsCoveragePercentageTest() {
        Project project = buildProject();

        when(projectService.getProjectById(project.getProjectId())).thenReturn(project);

        ProjectParams params = buildProjectParams();

        rulesService.saveRules(params, project.getProjectId());

        assertEquals(80.0f, project.getProjectParams().getRequiredCoveragePercentage());
    }

    @Test
    void projectParamsCodeRatingTest() {
        Project project = buildProject();

        when(projectService.getProjectById(project.getProjectId())).thenReturn(project);

        ProjectParams params = buildProjectParams();

        rulesService.saveRules(params, project.getProjectId());

        assertEquals(Rating.B, project.getProjectParams().getRequiredCodeRating());
    }

    @Test
    void executeProjectValidInputTest() {
        Project project = buildCompleteProject();
        UUID projectId = project.getProjectId();

        ProjectParamsRequestDTO paramsRequestDTO = new ProjectParamsRequestDTO(
                "Updated Project Name",
                "Updated Description",
                true,
                80.0f,
                true,
                Rating.B
        );

        // Mock projectService behavior to return the existing project
        when(projectService.getProjectById(projectId)).thenReturn(project);

        rulesService.executeProject(projectId, paramsRequestDTO);

        verify(projectService).calculateProjectCoverage(projectId);
        verify(projectService).calculateProjectRating(projectId);
        verify(projectService).calculateProjectStatus(projectId);

        Project updatedProject = projectService.getProjectById(projectId);

        assertEquals("Updated Project Name", updatedProject.getName());
        assertEquals("Updated Description", updatedProject.getDescription());
        assertEquals(Rating.A, updatedProject.getProjectResults().getCodeAnalysisResult().getActualRating());
    }
}
