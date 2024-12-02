package com.softdevsix.application.services.Rules;

import com.softdevsix.application.services.Project.IProjectService;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RulesService implements IRulesService {
    private final IProjectService PROJECT_SERVICE;

    public RulesService(IProjectService projectService) {
        this.PROJECT_SERVICE = projectService;
    }

    @Override
    public void saveRules(ProjectParams projectParams, UUID projectId) {
        Project project = PROJECT_SERVICE.getProjectById(projectId);

        project.setProjectParams(projectParams);
    }

    @Override
    public void executeProject() {

    }
}
