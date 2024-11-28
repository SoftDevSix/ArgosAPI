package com.softdevsix.services;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.domain.entities.file.MethodCoverageResult;
import com.softdevsix.exceptions.FileNotFoundException;
import com.softdevsix.repositories.IFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileService implements IFileService {

    @Autowired
    private IFileRepository fileRepository;

    @Override
    public void saveFile(File file) {
        fileRepository.save(file);
    }

    @Override
    public File getFile(UUID fileId) throws FileNotFoundException {
        return fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found"));
    }

    @Override
    public void deleteFile(UUID fileId) {
        fileRepository.deleteById(fileId);
    }

    @Override
    public FileCoverageResult processCoverage(UUID fileId) {
        File file = null;
        try {
            file = getFile(fileId);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int totalStatements = 0;
        int coveredStatements = 0;

        List<MethodCoverageResult> allMethodCoverageResults = file.getFileCoverageResults().stream()
                .flatMap(fileCoverageResult -> fileCoverageResult.getAllStatements().stream())
                .collect(Collectors.toList());

        for (MethodCoverageResult methodCoverageResult : allMethodCoverageResults) {
            Map<Integer, Boolean> statements = methodCoverageResult.getStatements();

            totalStatements += statements.size();
            coveredStatements += statements.values().stream()
                    .filter(Boolean::booleanValue)
                    .count();
        }

        int coveragePercentage = totalStatements == 0 ? 0 : (int) ((coveredStatements / (double) totalStatements) * 100);

        FileCoverageResult fileCoverageResult = FileCoverageResult.builder()
                .id(file.getFileId())
                .allStatements(allMethodCoverageResults)
                .build();

        return fileCoverageResult;
    }
}
