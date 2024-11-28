package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.domain.entities.project.ProjectParams;
import com.softdevsix.api.domain.entities.project.ProjectResults;
import com.softdevsix.api.domain.staticanalysis.Rating;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectService {
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

        ProjectResults results = ProjectResults.builder()
                .projectId(project.getProjectId())
                .build();

        return project;
    }

    @Test
    public void calculateProjectCoverageTest() {
        Project project = Project.builder()
                .projectId(UUID.randomUUID())
                .projectParams(ProjectParams.builder().build())
                .build();
    }
}
