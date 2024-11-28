package com.softdevsix.api.controllers;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.file.FileCoverageResult;
import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.services.IFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/coverage/file/")
public class FileCoverageController {
    private final IFileService fileService;

    public FileCoverageController(IFileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{idFile}")
    public ResponseEntity<FileCoverageResult> getProjectCoverage(@PathVariable UUID idFile) {
        File file = fileService.getFileById(idFile);
          return null;
    }

}
