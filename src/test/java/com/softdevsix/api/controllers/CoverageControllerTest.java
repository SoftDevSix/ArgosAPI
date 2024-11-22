package com.softdevsix.api.controllers;

import com.softdevsix.application.dtos.PullRequestCoverageDTO;
import com.softdevsix.application.services.IPullRequestCoverageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoverageController.class)
class CoverageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPullRequestCoverageService pullRequestCoverageService;

    private UUID pullRequestId;
    private PullRequestCoverageDTO pullRequestCoverageDTO;
    private SimpleDateFormat dateFormat;

    @BeforeEach
    void setUp() {
        pullRequestId = UUID.randomUUID();
        pullRequestCoverageDTO = new PullRequestCoverageDTO(
                pullRequestId,
                10,
                true,
                80,
                80,
                "A",
                "A",
                new Date()
        );
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Test
    void testGetPullRequestCoverageById() throws Exception {
        when(pullRequestCoverageService.getByPullRequestId(any(UUID.class))).thenReturn(pullRequestCoverageDTO);

        mockMvc.perform(get("/coverage/{pullRequestId}", pullRequestId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pullRequestId").value(pullRequestCoverageDTO.pullRequestId().toString()))
                .andExpect(jsonPath("$.totalAnalyzedFiles").value(pullRequestCoverageDTO.totalAnalyzedFiles()))
                .andExpect(jsonPath("$.status").value(pullRequestCoverageDTO.status()))
                .andExpect(jsonPath("$.coveragePercentage").value(pullRequestCoverageDTO.coveragePercentage()))
                .andExpect(jsonPath("$.requiredCoveragePercentage").value(pullRequestCoverageDTO.requiredCoveragePercentage()))
                .andExpect(jsonPath("$.codeRating").value(pullRequestCoverageDTO.codeRating()))
                .andExpect(jsonPath("$.requiredCodeRating").value(pullRequestCoverageDTO.requiredCodeRating()));
    }

    @Test
    void testAddCoverage() throws Exception {
        when(pullRequestCoverageService.addPullRequestCoverage(any(PullRequestCoverageDTO.class))).thenReturn(true);

        String content = String.format(
                "{\"pullRequestId\":\"%s\",\"totalAnalyzedFiles\":%d,\"status\":%b,\"coveragePercentage\":%f,\"requiredCoveragePercentage\":%f,\"codeRating\":\"%s\",\"requiredCodeRating\":\"%s\",\"analysisDate\":\"%s\"}",
                pullRequestCoverageDTO.pullRequestId(),
                pullRequestCoverageDTO.totalAnalyzedFiles(),
                pullRequestCoverageDTO.status(),
                pullRequestCoverageDTO.coveragePercentage(),
                pullRequestCoverageDTO.requiredCoveragePercentage(),
                pullRequestCoverageDTO.codeRating(),
                pullRequestCoverageDTO.requiredCodeRating(),
                dateFormat.format(pullRequestCoverageDTO.analysisDate())
        );

        mockMvc.perform(post("/coverage/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testUpdateCoverage() throws Exception {
        when(pullRequestCoverageService.updatePullRequestCoverage(any(UUID.class), any(PullRequestCoverageDTO.class))).thenReturn(pullRequestCoverageDTO);

        String content = String.format(
                "{\"pullRequestId\":\"%s\",\"totalAnalyzedFiles\":%d,\"status\":%b,\"coveragePercentage\":%f,\"requiredCoveragePercentage\":%f,\"codeRating\":\"%s\",\"requiredCodeRating\":\"%s\",\"analysisDate\":\"%s\"}",
                pullRequestCoverageDTO.pullRequestId(),
                pullRequestCoverageDTO.totalAnalyzedFiles(),
                pullRequestCoverageDTO.status(),
                pullRequestCoverageDTO.coveragePercentage(),
                pullRequestCoverageDTO.requiredCoveragePercentage(),
                pullRequestCoverageDTO.codeRating(),
                pullRequestCoverageDTO.requiredCodeRating(),
                dateFormat.format(pullRequestCoverageDTO.analysisDate())
        );

        mockMvc.perform(patch("/coverage/{pullRequestId}", pullRequestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pullRequestId").value(pullRequestCoverageDTO.pullRequestId().toString()))
                .andExpect(jsonPath("$.totalAnalyzedFiles").value(pullRequestCoverageDTO.totalAnalyzedFiles()))
                .andExpect(jsonPath("$.status").value(pullRequestCoverageDTO.status()))
                .andExpect(jsonPath("$.coveragePercentage").value(pullRequestCoverageDTO.coveragePercentage()))
                .andExpect(jsonPath("$.requiredCoveragePercentage").value(pullRequestCoverageDTO.requiredCoveragePercentage()))
                .andExpect(jsonPath("$.codeRating").value(pullRequestCoverageDTO.codeRating()))
                .andExpect(jsonPath("$.requiredCodeRating").value(pullRequestCoverageDTO.requiredCodeRating()));
    }

    @Test
    void testDeleteCoverage() throws Exception {
        when(pullRequestCoverageService.deletePullRequestCoverage(any(UUID.class))).thenReturn(true);

        mockMvc.perform(delete("/coverage/{pullRequestId}", pullRequestId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}