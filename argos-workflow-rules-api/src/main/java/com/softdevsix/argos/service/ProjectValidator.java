package com.softdevsix.argos.service;

import com.softdevsix.argos.domain.Project;
import com.softdevsix.argos.domain.ProjectRules;
import com.softdevsix.argos.exception.ProjectNotFoundException;
import com.softdevsix.argos.repository.ProjectRepository;
import com.softdevsix.argos.repository.ProjectRulesRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProjectValidator {

  private final ProjectRepository projectRepository;
  private final ProjectRulesRepository projectRulesRepository;

  public ProjectValidator(
      ProjectRepository projectRepository, ProjectRulesRepository projectRulesRepository) {
    this.projectRepository = projectRepository;
    this.projectRulesRepository = projectRulesRepository;
  }

  public Project validate(Integer projectId) {
    validateProjectAvailability(projectId);
    return validateProjectExistence(projectId);
  }

  private void validateProjectAvailability(Integer projectId) {
    Optional<ProjectRules> optional = projectRulesRepository.findById(projectId);
    if (optional.isPresent()) {
        throw new IllegalArgumentException("Project already has rules");
    }
  }

  private Project validateProjectExistence(Integer projectId) {
    Optional<Project> optional = projectRepository.findById(projectId);
    if (optional.isEmpty()) {
        throw new ProjectNotFoundException("Provided project ID is not registered");
    }
    return optional.get();
  }
}
