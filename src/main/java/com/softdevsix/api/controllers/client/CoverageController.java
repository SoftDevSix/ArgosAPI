package com.softdevsix.api.controllers.client;

import com.softdevsix.api.services.client.ICoverageService;
import com.softdevsix.api.services.report.IReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CoverageController {

    private final ICoverageService coverageService;
    private final IReportService reportService;

    public CoverageController(ICoverageService coverageService, IReportService reportService) {
        this.coverageService = coverageService;
        this.reportService = reportService;
    }

    @GetMapping("/api/coverage")
    public ResponseEntity<String> processCoverageFile(@RequestParam String projectId) {
        Optional<String> coverageFile = coverageService.getCoverageJson(projectId);

        if (coverageFile.isPresent()) {
            try {
                reportService.processAndSaveReport(coverageFile.get());
                return ResponseEntity.ok("Report processed and saved successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error processing report: " + e.getMessage());
            }
        }

        return ResponseEntity.notFound().build();
    }
}
