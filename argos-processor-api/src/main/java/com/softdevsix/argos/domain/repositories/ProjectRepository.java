package com.softdevsix.argos.domain.repositories;

import com.softdevsix.argos.domain.entities.project.Project;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
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

    public List<Project> getAll(){
        return projects.values().stream().toList();
    }
}
