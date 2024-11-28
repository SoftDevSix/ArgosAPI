package com.softdevsix.api.controllers;

import com.softdevsix.api.domain.coverage.ProjectCoverageResult;
import com.softdevsix.api.services.IProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coverage")
public class ProjectCoverageController {
    private final IProjectService PROJECT_SERVICE;

    public ProjectCoverageController(IProjectService projectService) {
        this.PROJECT_SERVICE = projectService;
    }

    @GetMapping
    public ResponseEntity<ProjectCoverageResult> getProjectCoverage() {
        return null;
    }
}
