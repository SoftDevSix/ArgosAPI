package com.softdevsix.api.controllers;

import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.exceptions.ProjectNotFoundException;
import com.softdevsix.api.mappers.requests.ProjectCreateRequest;
import com.softdevsix.api.mappers.responses.ProjectResponse;
import com.softdevsix.api.services.IProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Create a new Project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return the created Project", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProjectResponse.class))),
    })
    @PostMapping("/")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectCreateRequest projectCreateRequest) {
        ProjectResponse createdProject = projectService.createProject(projectCreateRequest);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Project by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the Project", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProjectResponse.class))),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content)
    })
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable UUID projectId) throws ProjectNotFoundException {
        Project project = projectService.getProject(projectId);
        return project != null ? (ResponseEntity<ProjectResponse>) ResponseEntity.ok() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete Project by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Project deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content)
    })
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID projectId) throws ProjectNotFoundException {
        boolean isDeleted = projectService.deleteProject(projectId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}