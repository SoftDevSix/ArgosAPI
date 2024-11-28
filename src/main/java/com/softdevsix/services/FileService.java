package com.softdevsix.services;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.exceptions.FileNotFoundException;
import com.softdevsix.repositories.IFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    @Autowired
    private IFileRepository fileRepository;

    @Override
    public void saveFile(File file) {

    }

    @Override
    public File getFile(UUID fileId) throws FileNotFoundException {
        return null;
    }

    @Override
    public void deleteFile(UUID fileId) {

    }

    @Override
    public FileCoverageResult processCoverage(File file) {
        return null;
    }
}
