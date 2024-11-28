package com.softdevsix.api.services;

import com.softdevsix.api.repositories.IProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {
    private IProjectRepository PROJECT_REPOSITORY;

    public ProjectService(IProjectRepository projectRepository) {
        this.PROJECT_REPOSITORY = projectRepository;
    }


}
