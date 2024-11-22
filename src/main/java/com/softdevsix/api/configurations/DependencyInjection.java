package com.softdevsix.api.configurations;

import com.softdevsix.application.repositories.IPullRequestCoverageRepository;
import com.softdevsix.application.services.IPullRequestCoverageService;
import com.softdevsix.infrastructure.repositories.PullRequestCoverageRepository;
import com.softdevsix.infrastructure.services.PullRequestCoverageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyInjection {

    @Bean
    public IPullRequestCoverageRepository pullRequestCoverageRepository() {
        return new PullRequestCoverageRepository();
    }

    @Bean
    public IPullRequestCoverageService pullRequestCoverageService(IPullRequestCoverageRepository pullRequestCoverageRepository) {
        return new PullRequestCoverageService(pullRequestCoverageRepository);
    }
}
