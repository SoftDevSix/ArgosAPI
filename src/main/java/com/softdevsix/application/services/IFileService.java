package com.softdevsix.application.services;

import com.softdevsix.domain.entities.file.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFileService {
    Optional<File> getFileById(UUID fileId);
    float calculateFileCoverage(File file);
    float calculateFileMethodCoverage(File file);
    List<Integer> getUncoveredLines(File file);
    List<File> getAll();
}
