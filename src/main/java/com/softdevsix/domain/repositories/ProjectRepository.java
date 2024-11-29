package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.project.Project;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
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
