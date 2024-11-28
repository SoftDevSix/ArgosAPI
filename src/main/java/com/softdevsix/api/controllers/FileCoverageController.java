package com.softdevsix.api.controllers;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.dto.FileCoverageDto;
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
    public ResponseEntity<FileCoverageDto> getFileCoverage(@PathVariable UUID idFile) {
        File file = fileService.getFileById(idFile);

        FileCoverageDto dto = new FileCoverageDto(
                file.getFileName(),
                file.getPath(),
                file.getLineCode(),
                fileService.calculateFileMethodCoverage(file),
                fileService.calculateFileCoverage(file)
        );

        return ResponseEntity.ok(dto);
    }
}