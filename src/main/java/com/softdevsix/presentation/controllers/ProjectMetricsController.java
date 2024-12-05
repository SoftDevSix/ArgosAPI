package com.softdevsix.presentation.controllers;

import com.softdevsix.application.dto.CodeAnalysisResultDTO;
import com.softdevsix.application.dto.CoverageResultDTO;
import com.softdevsix.application.dto.ProjectResultDTO;
import com.softdevsix.domain.entities.project.ProjectResults;
import com.softdevsix.application.services.Project.IProjectService;
import com.softdevsix.domain.exceptions.ProjectNotFoundException;
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
    public ResponseEntity<ProjectResultDTO> getProjectMetrics(@PathVariable UUID id) {
        try {
            ProjectResults results = projectService.getProjectResults(id);
            ProjectResultDTO resultDTO = ProjectResultDTO.builder()
                    .status(results.getStatus())
                    .coverageResult(CoverageResultDTO.builder()
                            .requiredCoverage(results.getCoverageResult().getRequiredCoverage())
                            .totalCoverage(results.getCoverageResult().getTotalCoverage())
                            .build())
                    .codeAnalysisResult(CodeAnalysisResultDTO.builder()
                            .expectedRating(results.getCodeAnalysisResult().getExpectedRating())
                            .actualRating(results.getCodeAnalysisResult().getActualRating())
                            .build())
                    .build();
            return new ResponseEntity<>(resultDTO, HttpStatus.OK);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
