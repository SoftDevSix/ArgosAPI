package com.softdevsix.controllers;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.exceptions.FileNotFoundException;
import com.softdevsix.services.IFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> saveFile(@RequestBody File file) {
        FILE_SERVICE.saveFile(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFileById(@RequestBody UUID id) {
        FILE_SERVICE.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<FileCoverageResult> getFileCoverageResults(@RequestBody File file) {
        FileCoverageResult result = FILE_SERVICE.processCoverage(file);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
