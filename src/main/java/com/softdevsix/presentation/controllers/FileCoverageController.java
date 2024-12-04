package com.softdevsix.presentation.controllers;

import com.softdevsix.application.services.IProjectService;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.application.dto.FileCoverageDto;
import com.softdevsix.application.services.File.IFileService;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.exceptions.ProjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/coverage/project/")
public class FileCoverageController {
    private final IFileService fileService;
    private final IProjectService projectService;

    public FileCoverageController(IFileService fileService, IProjectService projectService) {
        this.fileService = fileService;
        this.projectService = projectService;
    }

    /**
     * Note: We need to save the file manager path and there is no entity for that,
     * so it should be an equals and not a contains.
     * @param idProject project id
     * @param filePath file path
     * @return file coverage
     */
    @GetMapping("/{idProject}/")
    public ResponseEntity<FileCoverageDto> getFileCoverage(@PathVariable UUID idProject, @RequestParam String filePath) {
        Project project = projectService.getProjectById(idProject);

        UUID fileId = project.getCoveredFiles().stream().filter(f -> filePath.contains(f.getPath())).findFirst().orElseThrow(() -> new ProjectNotFoundException("File not found"))
                .getFileId();
        File file = fileService.getFileById(fileId);

        FileCoverageDto dto = new FileCoverageDto(
                file.getFileName(),
                file.getPath(),
                file.getLineCode(),
                fileService.calculateFileMethodCoverage(file),
                fileService.calculateFileCoverage(file),
                fileService.getUncoveredLines(file)
        );

        return ResponseEntity.ok(dto);
    }

}
