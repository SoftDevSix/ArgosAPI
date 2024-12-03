package com.softdevsix.application.services;

import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.exceptions.ProjectNotFoundException;
import com.softdevsix.domain.repositories.IRulesRepository;

import java.util.List;
import java.util.UUID;

public class RulesService implements IRulesService {
    private final IRulesRepository rulesRepository;

    public RulesService(IRulesRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }

    @Override
    public ProjectParams getProjectById(UUID projectId) {
        ProjectParams projectParams= rulesRepository.findById(projectId);
        if (projectParams == null) {
            throw new ProjectNotFoundException("Project with Id: " + projectId + " not found");
        }
        return projectParams;
    }

    @Override
    public List<ProjectParams> getAll() {
        return rulesRepository.getAll();
    }
}
