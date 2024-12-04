package com.softdevsix.presentation.controllers;

import com.softdevsix.application.services.IProjectService;
import com.softdevsix.domain.entities.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
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

//    @Test
//    void getProjectById_Success() {
//        UUID projectId = UUID.randomUUID();
//        Project mockProject = Project.builder()
//                .projectId(projectId)
//                .name("Project Name")
//                .description("Project Description")
//                .build();
//        when(projectService.getProjectById(projectId)).thenReturn(Optional.ofNullable(mockProject));
//
//        ResponseEntity<Object> response = projectController.getProjectById(projectId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Project Name", getFieldFromResponse(response, "projectName"));
//        assertEquals("Project Description", getFieldFromResponse(response, "projectDescription"));
//        verify(projectService, times(1)).getProjectById(projectId);
//    }
//
//    @Test
//    void getProjectById_NotFound() {
//        UUID projectId = UUID.randomUUID();
//        when(projectService.getProjectById(projectId)).thenThrow(new RuntimeException("Not Found"));
//
//        ResponseEntity<Object> response = projectController.getProjectById(projectId);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals("Project not found", response.getBody());
//        verify(projectService, times(1)).getProjectById(projectId);
//    }


    private String getFieldFromResponse(ResponseEntity<Object> response, String fieldName) {
        try {
            Object body = response.getBody();
            if (body != null) {
                var field = body.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                return (String) field.get(body);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error when accessing the field : " + fieldName, e);
        }
        return null;
    }
}
