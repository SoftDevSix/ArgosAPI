package com.softdevsix.infrastructure.repositories;

import com.softdevsix.domain.pullrequests.PullRequestCoverage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class PullRequestCoverageRepositoryTest {

    private PullRequestCoverageRepository repository;
    private PullRequestCoverage pullRequestCoverage;
    private UUID pullRequestId;

    @BeforeEach
    void setUp() {
        repository = new PullRequestCoverageRepository();
        pullRequestId = UUID.randomUUID();
        pullRequestCoverage = new PullRequestCoverage(UUID.randomUUID());
    }

    @Test
    void testAdd() {
        boolean result = repository.add(pullRequestCoverage);
        assertFalse(result, "The add method should return false.");
    }

    @Test
    void testGetById() {
        PullRequestCoverage result = repository.getById(pullRequestId);
        assertNull(result, "The getById method should return null.");
    }

    @Test
    void testUpdate() {
        PullRequestCoverage result = repository.update(pullRequestId, pullRequestCoverage);
        assertNull(result, "The update method should return null.");
    }

    @Test
    void testDelete() {
        boolean result = repository.delete(pullRequestId);
        assertFalse(result, "The delete method should return false.");
    }
}