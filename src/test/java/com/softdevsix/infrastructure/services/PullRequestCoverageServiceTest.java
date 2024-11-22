package com.softdevsix.infrastructure.services;

import com.softdevsix.application.dtos.PullRequestCoverageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PullRequestCoverageServiceTest {

    private PullRequestCoverageService service;
    private UUID pullRequestId;
    private PullRequestCoverageDTO pullRequestCoverageDTO;

    @BeforeEach
    void setUp() {
        service = new PullRequestCoverageService();
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
    }

    @Test
    void testGetByPullRequestId() {
        PullRequestCoverageDTO result = service.getByPullRequestId(pullRequestId);
        assertNotNull(result, "The getByPullRequestId method should return a non-null value.");
        assertEquals(pullRequestId, result.pullRequestId(), "The pullRequestId should match.");
    }

    @Test
    void testAddPullRequestCoverage() {
        boolean result = service.addPullRequestCoverage(pullRequestCoverageDTO);
        assertFalse(result, "The addPullRequestCoverage method should return false.");
    }

    @Test
    void testUpdatePullRequestCoverage() {
        PullRequestCoverageDTO result = service.updatePullRequestCoverage(pullRequestId, pullRequestCoverageDTO);
        assertNull(result, "The updatePullRequestCoverage method should return null.");
    }

    @Test
    void testDeletePullRequestCoverage() {
        boolean result = service.deletePullRequestCoverage(pullRequestId);
        assertFalse(result, "The deletePullRequestCoverage method should return false.");
    }
}