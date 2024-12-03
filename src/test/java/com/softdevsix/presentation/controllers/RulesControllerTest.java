package com.softdevsix.presentation.controllers;

import com.softdevsix.application.services.Rules.IRulesService;
import com.softdevsix.domain.entities.project.ProjectParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RulesControllerTest {
    @Mock
    private IRulesService rulesService;

    @InjectMocks
    private RulesController rulesController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rulesController).build();
    }

    @Test
    public void setRulesWithPostMethod() throws Exception {
        UUID projectId = UUID.randomUUID();

        String requestBody = """
                {
                    "id": "d3bce5ee-1b22-4a0e-8a32-3d5c2339a8b5",
                    "requiredCoveragePercentage": 80.0,
                    "requiredCodeRating": "B"
                }
                """;

        doNothing().when(rulesService).saveRules(
                org.mockito.ArgumentMatchers.any(ProjectParams.class),
                org.mockito.ArgumentMatchers.eq(projectId)
        );

        mockMvc.perform(post("/rules/{projectId}", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Project params added successfully."));
    }
}
