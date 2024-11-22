package com.softdevsix.infrastructure.repositories;

import com.softdevsix.application.repositories.IPullRequestCoverageRepository;
import com.softdevsix.domain.pullrequests.PullRequestCoverage;

import java.util.UUID;

public class PullRequestCoverageRepository implements IPullRequestCoverageRepository {
    @Override
    public boolean add(PullRequestCoverage entity) {
        return false;
    }

    @Override
    public PullRequestCoverage getById(UUID id) {
        return null;
    }

    @Override
    public PullRequestCoverage update(UUID id, PullRequestCoverage entity) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }
}
