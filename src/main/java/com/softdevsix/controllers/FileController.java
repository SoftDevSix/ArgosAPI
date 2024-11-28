package com.softdevsix.controllers;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.dtos.SaveFileDTO;
import com.softdevsix.exceptions.FileNotFoundException;
import com.softdevsix.services.IFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/argos-api/v1/coverage/file")
public class FileController {
    private final IFileService FILE_SERVICE;

    public FileController(IFileService fileService) {
        this.FILE_SERVICE = fileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable UUID id) {
        try {
            File file = FILE_SERVICE.getFile(id);
            return new ResponseEntity<>(file, HttpStatus.ACCEPTED);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<File> saveFile(@RequestBody SaveFileDTO fileDTO) {
        File file = File.builder()
                .fileName(fileDTO.getFileName())
                .path(fileDTO.getPath())
                .build();

        FILE_SERVICE.saveFile(file);

        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFileById(@RequestBody UUID id) {
        try {
            FILE_SERVICE.getFile(id);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FILE_SERVICE.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<List<FileCoverageResult>> getFileCoverageResults(@RequestBody File file) {
        if (file.getFileCoverageResults() == null) {
            FileCoverageResult result = FILE_SERVICE.processCoverage(file.getFileId());
            List<FileCoverageResult> resultFileCoverageResult = Collections.singletonList(result);
            return new ResponseEntity<>(resultFileCoverageResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(file.getFileCoverageResults(), HttpStatus.OK);
        }

    }
}