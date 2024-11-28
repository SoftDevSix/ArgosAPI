package com.softdevsix.services;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.exceptions.FileNotFoundException;

import java.util.UUID;

public interface IFileService {

    void saveFile(File file);
    File getFile(UUID fileId) throws FileNotFoundException;
    void deleteFile(UUID fileId);
    FileCoverageResult processCoverage(UUID fileId) throws FileNotFoundException;

}
