package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.file.MethodCoverageResult;
import com.softdevsix.api.exceptions.ProjectNotFoundException;
import com.softdevsix.api.repositories.IFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Service class to handle file coverage calculations and data retrieval.
 * Implements the IFileService interface.
 */
@Service
public class FileService implements IFileService {
    private IFileRepository fileRepository;

    public FileService(IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Sets the file repository. This method is used by Spring's dependency injection.
     *
     * @param fileRepository The repository for accessing file data.
     */
    @Autowired
    public void setFileRepository(IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Retrieves a file by its ID.
     *
     * @param fileId The UUID of the file.
     * @return The file with the specified ID.
     * @throws ProjectNotFoundException If the file with the given ID is not found.
     */
    @Override
    public File getFileById(UUID fileId) {
        return fileRepository.findById(fileId).orElseThrow(() ->
                new ProjectNotFoundException("Project with Id: " + fileId + " not found")
        );
    }

    /**
     * Calculates the average method coverage for a file.
     *
     * @param file The file whose coverage is to be calculated.
     * @return The average method coverage percentage.
     */
    @Override
    public float calculateFileCoverage(File file) {
        float totalMethodCoverage = 0f;
        int methodCount = 0;

        for (MethodCoverageResult methodCoverage : file.getMethodCoverageResults()) {
            int totalStatements = methodCoverage.getStatements().size();
            int coveredStatements = (int) methodCoverage.getStatements().values().stream().filter(Boolean::booleanValue).count();

            if (totalStatements > 0) {
                totalMethodCoverage += (coveredStatements * 100f) / totalStatements;
                methodCount++;
            }
        }

        return methodCount > 0 ? totalMethodCoverage / methodCount : 0f;
    }

    /**
     * Calculates the overall statement coverage for a file.
     *
     * @param file The file whose statement coverage is to be calculated.
     * @return The total statement coverage percentage.
     */
    @Override
    public float calculateFileMethodCoverage(File file) {
        int totalStatements = 0;
        int coveredStatements = 0;

        for (MethodCoverageResult methodCoverage : file.getMethodCoverageResults()) {
            for (Map.Entry<Integer, Boolean> entry : methodCoverage.getStatements().entrySet()) {
                totalStatements++;
                if (entry.getValue()) {
                    coveredStatements++;
                }
            }
        }
        return (totalStatements > 0) ? (coveredStatements * 100f) / totalStatements : 0f;
    }

    /**
     * Retrieves the list of uncovered lines in the file.
     *
     * @param file The file whose uncovered lines are to be retrieved.
     * @return An array of integers representing the line numbers of uncovered lines.
     */
    @Override
    public Integer[] getUncoveredLines(File file) {
        List<Integer> uncoveredLines = new ArrayList<>();
        for (MethodCoverageResult methodCoverage : file.getMethodCoverageResults()) {
            for (Map.Entry<Integer, Boolean> entry : methodCoverage.getStatements().entrySet()) {
                if (!entry.getValue()) {
                    uncoveredLines.add(entry.getKey());
                }
            }
        }
        return (Integer[]) uncoveredLines.toArray();
    }

}