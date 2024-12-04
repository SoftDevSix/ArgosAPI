package com.softdevsix.presentation.controllers;

import com.softdevsix.domain.entities.project.ProjectResults;
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
public class ProjectMetricsController {
    private final IProjectService projectService;

    public ProjectMetricsController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResults> getProjectMetrics(@PathVariable UUID id) {
//        try {
//            ProjectResults results = projectService.calculateProjectResults(id);
//            return new ResponseEntity<>(results, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return null;
    }
}
