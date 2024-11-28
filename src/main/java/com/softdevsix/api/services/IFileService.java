package com.softdevsix.api.services;

import com.softdevsix.api.domain.entities.file.File;

import java.util.List;
import java.util.UUID;

public interface IFileService {
    File getFileById(UUID fileId);
    float calculateFileCoverage(List<File> files);
    float calculateFileMethodCoverage(File file);

}
