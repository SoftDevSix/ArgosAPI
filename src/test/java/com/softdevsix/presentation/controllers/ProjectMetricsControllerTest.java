package com.softdevsix.presentation.controllers;

import com.softdevsix.domain.entities.project.ProjectResults;
import com.softdevsix.application.services.IProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProjectMetricsControllerTest {
    @Mock
    private IProjectService projectService;

    @InjectMocks
    private ProjectMetricsController projectMetricsController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectMetricsController).build();
    }

//    @Test
//    void testGetProjectMetrics_Success() throws Exception {
//        UUID projectId = UUID.randomUUID();
//        ProjectResults mockResults = ProjectResults.builder()
//                .id(projectId)
//                .build();
//        when(projectService.calculateProjectResults(projectId)).thenReturn(mockResults);
//
//        mockMvc.perform(get("/coverage/project/{id}", projectId))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testGetProjectMetrics_NotFound() throws Exception {
//        UUID projectId = UUID.randomUUID();
//        when(projectService.calculateProjectResults(projectId)).thenThrow(new RuntimeException("Project not found"));
//
//        mockMvc.perform(get("/coverage/project/{id}", projectId))
//                .andExpect(status().isNotFound());
//    }
}
