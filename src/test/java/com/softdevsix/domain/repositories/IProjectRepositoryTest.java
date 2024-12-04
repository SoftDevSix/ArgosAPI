package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IProjectRepositoryTest {
  @Autowired
  private IProjectRepository projectRepository;

  @Autowired
  private IProjectParamsRepository projectParamsRepository;

  @Test
  public void testSaveProjectWithParams() {
    ProjectParams projectParams = ProjectParams.builder()
            .id(UUID.randomUUID())
            .requiredCoveragePercentage(100.0f)
            .requiredCodeRating("C")
            .build();

    projectParamsRepository.save(projectParams);
    assertNotNull(projectParams.getId());

    Project project = Project.builder()
            .projectId(UUID.randomUUID())
            .name("Value")
            .description("Test description")
            .projectParams(projectParams)
            .build();

    Project savedProject = projectRepository.save(project);
    assertNotNull(savedProject.getProjectId());
    assertThat(savedProject.getName()).isEqualTo("Value");
    assertThat(savedProject.getProjectParams()).isNotNull();
    assertThat(savedProject.getProjectParams().getRequiredCoveragePercentage()).isEqualTo(100.0f);
    assertThat(savedProject.getProjectParams().getRequiredCodeRating()).isEqualTo("C");
  }
}
