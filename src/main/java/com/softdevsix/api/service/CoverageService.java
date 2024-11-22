package com.softdevsix.api.service;

import com.softdevsix.api.dtos.FileCoverageDTO;

/**
 * Interface for coverage service operations.
 */
public interface CoverageService {

    /**
     * Calculates and retrieves coverage details for a specific file.
     *
     * @param fileId The ID of the file for which the coverage is to be retrieved.
     * @return A {@link FileCoverageDTO} containing the coverage details.
     */
    FileCoverageDTO getFileCoverage(String fileId);
}
