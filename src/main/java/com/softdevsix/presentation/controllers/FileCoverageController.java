package com.softdevsix.presentation.controllers;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.application.dto.FileCoverageDto;
import com.softdevsix.application.services.File.IFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
                file.getCoverageResult().getMethodCoveragePercentage(),
                file.getCoverageResult().getCoveragePercentage(),
                fileService.getUncoveredLines(file)
        );

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<File>> getAll() {
        return ResponseEntity.ok(fileService.getAll());
    }
}
