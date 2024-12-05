package com.softdevsix.domain.repositories.project;

import com.softdevsix.domain.entities.project.Project;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProjectRepository implements IProjectRepository {
    private Map<UUID, Project> projects;

    public ProjectRepository() {
        this.projects = new HashMap<>();
    }

    @Override
    public Project createProject(Project project) {
        projects.put(project.getProjectId(), project);
        return project;
    }

    @Override
    public Project updateProject(Project project) {
        if (project == null || project.getProjectId() == null) {
            return null;
        }

        if (!projects.containsKey(project.getProjectId())) {
            return null;
        }
        projects.put(project.getProjectId(), project);
        return project;
    }

    public Project findById(UUID projectId) {
        return projects.get(projectId);
    }

    public void update(Project project) {projects.put(project.getProjectId(), project);}

    public List<Project> getAll(){
        return projects.values().stream().toList();
    }

    @Override
    public boolean deleteFile(UUID projectId) {
        return projects.remove(projectId) != null;
    }
}
