package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.file.MethodCoverageResult;
import com.softdevsix.api.exceptions.ProjectNotFoundException;
import com.softdevsix.api.repositories.IFileRepository;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileService implements IFileService {
    private final IFileRepository fileRepository;

    public FileService(IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File getFileById(UUID fileId) {
        return fileRepository.findById(fileId).orElseThrow(() ->
                new ProjectNotFoundException("Project with Id: " + fileId + " not found")
        );
    }

    @Override
    public float calculateFileCoverage(List<File> files) {
        float totalCoverage = 0f;

        for (File file : files) {
            float fileCoverage = calculateFileCoverageForFile(file);
            totalCoverage += fileCoverage;
        }

        return totalCoverage / files.size();
    }

    @Override
    public float calculateFileMethodCoverage(File file) {
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

    public static float calculateFileCoverageForFile(File file) {
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

}
