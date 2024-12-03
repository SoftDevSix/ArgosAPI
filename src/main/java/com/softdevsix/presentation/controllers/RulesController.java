package com.softdevsix.presentation.controllers;

import com.softdevsix.application.services.IRulesService;
import com.softdevsix.domain.entities.project.ProjectParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects/rules")
public class RulesController {

    private final IRulesService rulesService;

    public RulesController(IRulesService rulesServices) {
        this.rulesService = rulesServices;
    }

    @GetMapping
    public ResponseEntity<List<ProjectParams>> getAllProjects() {
        List<ProjectParams> projects = rulesService.getAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectParams> getProjectById(@PathVariable UUID id) {
        ProjectParams project = rulesService.getProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
