package com.softdevsix.api.repositories;


import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.exceptions.FileNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProjectRepository {
    Project findById(UUID id);
    void save(Project project);
}
