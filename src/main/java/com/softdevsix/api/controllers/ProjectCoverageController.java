package com.softdevsix.api.controllers;

import com.softdevsix.api.domain.coverage.ProjectCoverageResult;
import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.domain.entities.project.ProjectResults;
import com.softdevsix.api.services.IProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/coverage")
public class ProjectCoverageController {
    private final IProjectService PROJECT_SERVICE;

    public ProjectCoverageController(IProjectService projectService) {
        this.PROJECT_SERVICE = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResults> getProjectCoverage(@PathVariable UUID id) {
        try {
            Project project = PROJECT_SERVICE.getProjectById(id);

            return new ResponseEntity<>(project.getProjectResults(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
