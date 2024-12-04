package com.softdevsix.presentation.controllers;

import com.softdevsix.application.services.IProjectService;
import com.softdevsix.domain.entities.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    private IProjectService projectService;
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        projectService = mock(IProjectService.class);
        projectController = new ProjectController(projectService);
    }

    @Test
    void getProjectById_Success() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        Project mockProject = Project.builder()
                .projectId(projectId)
                .name("Project Name")
                .description("Project Description")
                .build();
        when(projectService.getProjectById(projectId)).thenReturn(mockProject);

        // Act
        ResponseEntity<Object> response = projectController.getProjectById(projectId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Project Name", getFieldFromResponse(response, "projectName"));
        assertEquals("Project Description", getFieldFromResponse(response, "projectDescription"));
        verify(projectService, times(1)).getProjectById(projectId);
    }

    @Test
    void getProjectById_NotFound() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        when(projectService.getProjectById(projectId)).thenThrow(new RuntimeException("Not Found"));

        // Act
        ResponseEntity<Object> response = projectController.getProjectById(projectId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Project not found", response.getBody());
        verify(projectService, times(1)).getProjectById(projectId);
    }

    /**
     * Método auxiliar para acceder a campos privados de ProjectResponse.
     */
    private String getFieldFromResponse(ResponseEntity<Object> response, String fieldName) {
        try {
            Object body = response.getBody();
            if (body != null) {
                var field = body.getClass().getDeclaredField(fieldName);
                field.setAccessible(true); // Permite acceso a campos privados
                return (String) field.get(body);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error al acceder al campo: " + fieldName, e);
        }
        return null;
    }
}
