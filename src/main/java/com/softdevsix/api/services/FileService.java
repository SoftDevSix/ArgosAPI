package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.file.MethodCoverageResult;
import com.softdevsix.api.exceptions.ProjectNotFoundException;
import com.softdevsix.api.repositories.IFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService implements IFileService {
    private IFileRepository fileRepository;

    public FileService(IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Autowired
    public void setFileRepository(IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    @Override
    public File getFileById(UUID fileId) {
        return fileRepository.findById(fileId).orElseThrow(() ->
                new ProjectNotFoundException("Project with Id: " + fileId + " not found")
        );
    }

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

}