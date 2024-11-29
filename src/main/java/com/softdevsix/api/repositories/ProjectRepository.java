package com.softdevsix.api.repositories;

import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.exceptions.FileNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProjectRepository implements IProjectRepository {
    Map<UUID, Project> projects;

    public ProjectRepository() {
        this.projects = new HashMap<>();
    }

    public Project findById(UUID id) {
        return this.projects.get(id);
    }

    public void save(Project project) {
        projects.put(project.getProjectId(), project);
    }
}
