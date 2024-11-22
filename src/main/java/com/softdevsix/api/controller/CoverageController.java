package com.softdevsix.api.controller;

import com.softdevsix.api.dtos.FileCoverageDTO;
import com.softdevsix.api.service.CoverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller to manage operations related to file coverage.
 */
@RestController
@RequestMapping("/coverage")
public class CoverageController {
    private final CoverageService coverageService;

    /**
     * Constructor to inject the coverage service implementation.
     *
     * @param coverageService Service that handles the business logic for coverage.
     */
    @Autowired
    public CoverageController(CoverageService coverageService) {
        this.coverageService = coverageService;
    }

    /**
     * Retrieves the coverage details of a specific file by its ID.
     *
     * @param fileId The ID of the file for which the coverage is to be retrieved.
     * @return A {@link ResponseEntity} containing a {@link FileCoverageDTO} with the coverage details.
     */
    @GetMapping("/file")
    public ResponseEntity<FileCoverageDTO> getCoverageFile(@RequestParam String fileId) {
        FileCoverageDTO fileCoverage = coverageService.getFileCoverage(fileId);
        return ResponseEntity.ok(fileCoverage);
    }
}
