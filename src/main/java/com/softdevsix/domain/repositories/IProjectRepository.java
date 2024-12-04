package com.softdevsix.domain.repositories;


import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.Project;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProjectRepository {

    void update(Project project);
    Project createProject(Project project);
    Project updateProject(Project project);
    Project findById(UUID proyectId);
    List<Project> getAll();
    boolean deleteFile(UUID ProjectId);
}
