package com.softdevsix.application.services;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.ProjectParams;

import java.util.List;
import java.util.UUID;

public interface IRulesService {
    ProjectParams getProjectById(UUID projectId);
    List<ProjectParams> getAll();

}
