package com.softdevsix.api.repositories;


import com.softdevsix.api.domain.entities.project.Project;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProjectRepository {
    Project findById(UUID id);

    void save(Project project);

    List<Project> getAll();
}
