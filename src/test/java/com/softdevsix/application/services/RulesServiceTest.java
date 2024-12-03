package com.softdevsix.application.services;

import com.softdevsix.application.services.Project.IProjectService;
import com.softdevsix.application.services.Rules.IRulesService;
import com.softdevsix.application.services.Rules.RulesService;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RulesServiceTest {

    private IProjectService projectService;
    private IRulesService rulesService;

    @BeforeEach
    public void setUp() {
        projectService = mock(IProjectService.class);
        rulesService = new RulesService(projectService);
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
    public void setProjectParamsNotNullTest() {
        Project project = buildProject();

        when(projectService.getProjectById(project.getProjectId())).thenReturn(project);

        ProjectParams params = buildProjectParams();

        rulesService.saveRules(params, project.getProjectId());

        assertNotNull(project.getProjectParams());
    }

    @Test
    public void projectParamsCoveragePercentageTest() {
        Project project = buildProject();

        when(projectService.getProjectById(project.getProjectId())).thenReturn(project);

        ProjectParams params = buildProjectParams();

        rulesService.saveRules(params, project.getProjectId());

        assertEquals(80.0f, project.getProjectParams().getRequiredCoveragePercentage());
    }

    @Test
    public void projectParamsCodeRatingTest() {
        Project project = buildProject();

        when(projectService.getProjectById(project.getProjectId())).thenReturn(project);

        ProjectParams params = buildProjectParams();

        rulesService.saveRules(params, project.getProjectId());

        assertEquals(Rating.B, project.getProjectParams().getRequiredCodeRating());
    }

}
