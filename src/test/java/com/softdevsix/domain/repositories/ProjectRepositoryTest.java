package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.repositories.project.IProjectRepository;
import com.softdevsix.domain.repositories.project.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProjectRepositoryTest {
    private IProjectRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProjectRepository();
    }

    @Test
    void updateProjectTest() {
        Project project = Project.builder()
                .projectId(UUID.randomUUID())
                .name("test")
                .description("test")
                .build();

        repository.createProject(project);

        project.setName("updated");
        project.setDescription("updated");

        repository.updateProject(project);

        assertEquals("updated", repository.findById(project.getProjectId()).getName());
        assertEquals("updated", repository.findById(project.getProjectId()).getDescription());
    }

    @Test
    void updateProjectWithDifferentIdTest() {
        Project project = Project.builder()
                .projectId(UUID.randomUUID())
                .name("test")
                .description("test")
                .build();

        repository.createProject(project);

        project.setProjectId(UUID.randomUUID());

        assertNull(repository.updateProject(project));
    }

    @Test
    void updateProjectWithNullIdTest() {
        Project project = Project.builder()
                .projectId(UUID.randomUUID())
                .name("test")
                .description("test")
                .build();

        repository.createProject(project);

        project.setProjectId(null);

        assertNull(repository.updateProject(project));
    }
}
