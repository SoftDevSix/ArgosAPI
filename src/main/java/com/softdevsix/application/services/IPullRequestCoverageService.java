package com.softdevsix.application.services;

import com.softdevsix.application.dtos.PullRequestCoverageDTO;

import java.util.UUID;

public interface IPullRequestCoverageService {
    PullRequestCoverageDTO getByPullRequestId(UUID pullRequestId);

    boolean addPullRequestCoverage(PullRequestCoverageDTO pullRequestCoverage);

    PullRequestCoverageDTO updatePullRequestCoverage(UUID id, PullRequestCoverageDTO pullRequestCoverage);

    boolean deletePullRequestCoverage(UUID id);
}
