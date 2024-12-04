package com.softdevsix.application.services;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.MethodCoverageResult;
import com.softdevsix.domain.exceptions.FileNotFoundException;
import com.softdevsix.domain.repositories.IFileRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FileService implements IFileService {
    private IFileRepository fileRepository;

    public FileService(@Qualifier("fileMemoryRepository") IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Optional<File> getFileById(UUID fileId) {
        Optional<File> file = fileRepository.findById(fileId);
        if (file.isEmpty()) {
            throw new FileNotFoundException("File with ID " + fileId + " not found.");
        }
        return file;
    }

    @Override
    public float calculateFileCoverage(File file) {
        float totalMethodCoverage = 0f;
        int methodCount = 0;

        for (MethodCoverageResult methodCoverage : file.getFileCoverageResult().getMethodCoverageResults()) {
            int totalStatements = methodCoverage.getMethodStatements().size();
            int coveredStatements = (int) methodCoverage.getMethodStatements().values().stream().filter(Boolean::booleanValue).count();

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

        for (MethodCoverageResult methodCoverage : file.getFileCoverageResult().getMethodCoverageResults()) {
            for (Map.Entry<Integer, Boolean> entry : methodCoverage.getMethodStatements().entrySet()) {
                totalStatements++;
                if (entry.getValue() != null && entry.getValue()) {
                    coveredStatements++;
                }
            }
        }

        return (totalStatements > 0) ? (coveredStatements * 100f) / totalStatements : 0f;
    }

    @Override
    public List<Integer> getUncoveredLines(File file) {
        List<Integer> uncoveredLines = new ArrayList<>();
        for (MethodCoverageResult methodCoverage : file.getFileCoverageResult().getMethodCoverageResults()) {
            for (Map.Entry<Integer, Boolean> entry : methodCoverage.getMethodStatements().entrySet()) {
                if (entry.getValue() != null && !entry.getValue().booleanValue()) {
                    uncoveredLines.add(entry.getKey());
                }
            }
        }
        return uncoveredLines;
    }


    @Override
    public List<File> getAll() {
        return fileRepository.findAll();
    }

}
