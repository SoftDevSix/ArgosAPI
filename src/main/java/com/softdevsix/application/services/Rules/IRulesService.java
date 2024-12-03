package com.softdevsix.application.services.Rules;

import com.softdevsix.application.dto.ProjectParamsRequestDTO;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;

import java.util.UUID;

public interface IRulesService {
    void saveRules(ProjectParams projectParams, UUID projectId);
    void executeProject(Project project, ProjectParamsRequestDTO paramsRequestDTO);
}
