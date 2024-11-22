package com.softdevsix.api.controllers;

import com.softdevsix.application.dtos.PullRequestCoverageDTO;
import com.softdevsix.application.services.IPullRequestCoverageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/coverage")
public class CoverageController {

    private final IPullRequestCoverageService pullRequestCoverageService;

    public CoverageController(IPullRequestCoverageService pullRequestCoverageService) {
        this.pullRequestCoverageService = pullRequestCoverageService;
    }

    @Operation(summary = "Get Pull Request Coverage by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the Pull Request Coverage", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PullRequestCoverageDTO.class))),
    })
    @GetMapping("/{pullRequestId}")
    public PullRequestCoverageDTO getPullRequestCoverageById(@PathVariable UUID pullRequestId) {
        return pullRequestCoverageService.getByPullRequestId(pullRequestId);
    }

    @Operation(summary = "Add Pull Request Coverage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return true if the Pull Request Coverage was added", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
    })
    @PostMapping("/")
    public boolean addCoverage(@RequestBody PullRequestCoverageDTO pullRequestCoverageDTO) {
        return pullRequestCoverageService.addPullRequestCoverage(pullRequestCoverageDTO);
    }

    @Operation(summary = "Update Pull Request Coverage by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the updated Pull Request Coverage", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PullRequestCoverageDTO.class))),
    })
    @PatchMapping("/{pullRequestId}")
    public PullRequestCoverageDTO updateCoverage(@PathVariable UUID pullRequestId, @RequestBody PullRequestCoverageDTO pullRequestCoverageDTO) {
        return pullRequestCoverageService.updatePullRequestCoverage(pullRequestId, pullRequestCoverageDTO);
    }

    @Operation(summary = "Delete Pull Request Coverage by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return true if the Pull Request Coverage was deleted", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
    })
    @DeleteMapping("/{pullRequestId}")
    public boolean deleteCoverage(@PathVariable UUID pullRequestId) {
        return pullRequestCoverageService.deletePullRequestCoverage(pullRequestId);
    }
}
