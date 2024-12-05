package com.softdevsix.application.services;

import com.softdevsix.application.services.file.IFileService;
import com.softdevsix.application.services.project.IProjectService;
import com.softdevsix.application.services.rules.IRulesService;
import com.softdevsix.application.services.rules.RulesService;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
