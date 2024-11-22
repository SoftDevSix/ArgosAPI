package com.softdevsix.infrastructure.services;

import com.softdevsix.application.dtos.PullRequestCoverageDTO;
import com.softdevsix.application.services.IPullRequestCoverageService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PullRequestCoverageService implements IPullRequestCoverageService {
    @Override
    public PullRequestCoverageDTO getByPullRequestId(UUID pullRequestId) {
        return new PullRequestCoverageDTO(pullRequestId, 10, true, 80, 80, "A", "A", new Date());
    }

    @Override
    public boolean addPullRequestCoverage(PullRequestCoverageDTO pullRequestCoverage) {
        return false;
    }

    @Override
    public PullRequestCoverageDTO updatePullRequestCoverage(UUID id, PullRequestCoverageDTO pullRequestCoverage) {
        return null;
    }

    @Override
    public boolean deletePullRequestCoverage(UUID id) {
        return false;
    }
}
