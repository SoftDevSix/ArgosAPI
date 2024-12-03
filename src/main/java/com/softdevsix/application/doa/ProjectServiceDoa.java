package com.softdevsix.application.doa;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProjectServiceDoa {
    ProjectRepositoryDoa projectRepositoryDoa;

    public ProjectDoa createProjectDoa(ProjectDoa doa){
        return projectRepositoryDoa.save(doa);
    }

    public ProjectDoa getProjectDoa(Long id) {
        return projectRepositoryDoa.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProjectDoa with ID " + id + " not found"));
    }

}
