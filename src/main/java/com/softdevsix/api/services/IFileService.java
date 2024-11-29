package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.file.File;

import java.util.List;
import java.util.UUID;

public interface IFileService {
    File getFileById(UUID fileId);
    float calculateFileCoverage(File file);
    float calculateFileMethodCoverage(File file);
    List<Integer> getUncoveredLines(File file);
    List<File> getAll();
}
