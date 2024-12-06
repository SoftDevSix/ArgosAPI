package com.softdevsix.domain.repositories.rules;

import com.softdevsix.domain.entities.project.ProjectParams;

import java.util.List;
import java.util.UUID;

public interface IRulesRepository {
    ProjectParams createRule(ProjectParams params);
    ProjectParams updateRule(ProjectParams params);
    ProjectParams findById(UUID paramsId);
    List<ProjectParams> getAll();
    boolean deleteRule(UUID paramsId);

}
