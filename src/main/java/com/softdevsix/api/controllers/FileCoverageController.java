package com.softdevsix.api.controllers;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.file.MethodCoverageResult;
import com.softdevsix.api.dto.FileCoverageDto;
import com.softdevsix.api.services.IFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.Scanner;
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
        System.out.println(file.getFileName());
        System.out.println(file.getFileId());

        FileCoverageDto dto = new FileCoverageDto(
                file.getFileName(),
                file.getPath(),
                file.getLineCode(),
                fileService.calculateFileMethodCoverage(file),
                fileService.calculateFileCoverage(file),
                fileService.getUncoveredLines(file)
        );
        System.out.println(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<File>> getAll() {
        return ResponseEntity.ok(fileService.getAll());
    }

}