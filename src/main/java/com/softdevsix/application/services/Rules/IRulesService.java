package com.softdevsix.application.services.Rules;

import com.softdevsix.domain.entities.project.ProjectParams;

import java.util.UUID;

public interface IRulesService {
    void saveRules(ProjectParams projectParams, UUID projectId);
    void executeProject();
}
