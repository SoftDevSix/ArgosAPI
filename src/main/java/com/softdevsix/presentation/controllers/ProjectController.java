package com.softdevsix.presentation.controllers;

import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.application.services.IProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/coverage/project")
public class ProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/description/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable UUID id) {
//        try {
//            Project project = projectService.getProjectById(id);
//
//            return new ResponseEntity<>(
//                    new ProjectResponse(project.getName(), project.getDescription()),
//                    HttpStatus.OK
//            );
//        } catch (Exception e) {
//            return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
//        }
        return null;
    }

    private static class ProjectResponse {
        private String projectName;
        private String projectDescription;

        public ProjectResponse(String projectName, String projectDescription) {
            this.projectName = projectName;
            this.projectDescription = projectDescription;
        }

        public String getProjectName() {
            return projectName;
        }

        public String getProjectDescription() {
            return projectDescription;
        }
    }
}

