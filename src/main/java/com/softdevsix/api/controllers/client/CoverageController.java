package com.softdevsix.api.controllers.client;

import com.softdevsix.api.services.client.ICoverageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CoverageController {

    private final ICoverageService coverageService;

    public CoverageController(ICoverageService coverageService) {
        this.coverageService = coverageService;
    }

    @GetMapping("/api/coverage")
    public ResponseEntity<String> getCoverageFile(@RequestParam String projectId) {
        Optional<String> coverageFile = coverageService.getCoverageJson(projectId);

        return coverageFile
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
