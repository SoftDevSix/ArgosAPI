package com.softdevsix.application.services.client;

import java.util.Optional;

public interface ICoverageService {
    Optional<String> getCoverageJson(String projectId);
}
