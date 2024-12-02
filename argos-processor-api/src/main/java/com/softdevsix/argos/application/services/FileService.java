package com.softdevsix.argos.application.services;

import com.softdevsix.argos.domain.entities.file.File;
import com.softdevsix.argos.domain.entities.file.MethodCoverageResult;
import com.softdevsix.argos.domain.exceptions.FileNotFoundException;
import com.softdevsix.argos.domain.repositories.IFileRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService implements IFileService {
    private IFileRepository fileRepository;

    public FileService(@Qualifier("fileMemoryRepository") IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @SneakyThrows
    @Override
    public File getFileById(UUID fileId) {
        File file = fileRepository.findById(fileId);
        if (file == null) {
            throw new FileNotFoundException("File with ID " + fileId + " not found.");
        }
        return file;
    }

    @Override
    public float calculateFileCoverage(File file) {
        float totalMethodCoverage = 0f;
        int methodCount = 0;

        for (MethodCoverageResult methodCoverage : file.getCoverageResult().getAllStatements()) {
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

        for (MethodCoverageResult methodCoverage : file.getCoverageResult().getAllStatements()) {
            for (Map.Entry<Integer, Boolean> entry : methodCoverage.getStatements().entrySet()) {
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
        for (MethodCoverageResult methodCoverage : file.getCoverageResult().getAllStatements()) {
            for (Map.Entry<Integer, Boolean> entry : methodCoverage.getStatements().entrySet()) {
                if (entry.getValue() != null && !entry.getValue().booleanValue()) {
                    uncoveredLines.add(entry.getKey());
                }
            }
        }
        return uncoveredLines;
    }


    @Override
    public List<File> getAll() {
        return fileRepository.getAll();
    }

}
