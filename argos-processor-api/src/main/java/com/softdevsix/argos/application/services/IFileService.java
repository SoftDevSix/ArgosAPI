package com.softdevsix.argos.application.services;

import com.softdevsix.argos.domain.entities.file.File;
import java.util.List;
import java.util.UUID;

public interface IFileService {
    File getFileById(UUID fileId);
    float calculateFileCoverage(File file);
    float calculateFileMethodCoverage(File file);
    List<Integer> getUncoveredLines(File file);
    List<File> getAll();
}
