package com.softdevsix.presentation.controllers;

import com.softdevsix.domain.repositories.client.IFileManagerClientRepository;
import com.softdevsix.application.services.report.IReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/coverage/create-notification")
public class CoverageNotificationController {

    private final IFileManagerClientRepository fileManagerClient;
    private final IReportService reportService;

    public CoverageNotificationController(IFileManagerClientRepository fileManagerClient, IReportService reportService) {
        this.fileManagerClient = fileManagerClient;
        this.reportService = reportService;
    }

    @PostMapping("/projectCreationNotification")
    public ResponseEntity<String> notifyProjectCreation(@RequestParam("id") String projectId) {
        try {
            Optional<String> coverageJson = fileManagerClient.getCoverageJson(projectId);

            if (coverageJson == null || coverageJson.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Coverage JSON not found for project: " + projectId);
            }
            reportService.processAndSaveReport(projectId, coverageJson.get());

            return ResponseEntity.ok("Coverage report processed successfully for project: " + projectId);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing coverage report: " + e.getMessage());
        }
    }
}
