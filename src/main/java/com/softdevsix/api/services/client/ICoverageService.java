package com.softdevsix.api.services.client;

import java.util.Optional;

public interface ICoverageService {
    Optional<String> getCoverageJson(String projectId);
}
