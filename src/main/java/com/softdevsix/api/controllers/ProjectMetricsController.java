package com.softdevsix.api.controllers;

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
@RequestMapping("/metrics")
public class ProjectMetricsController {
    private final IProjectService PROJECT_SERVICE;

    public ProjectMetricsController(IProjectService projectService) {
        this.PROJECT_SERVICE = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResults> getProjectMetrics(@PathVariable UUID id) {
        try {
            ProjectResults results = PROJECT_SERVICE.calculateProjectResults(id);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
