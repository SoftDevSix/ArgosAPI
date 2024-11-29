package com.softdevsix.api.services.client;

import com.softdevsix.api.exceptions.client.FileManagerException;
import com.softdevsix.api.repositories.client.IFileManagerClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoverageService implements ICoverageService {

    private final IFileManagerClientRepository fileManager;

    public CoverageService(IFileManagerClientRepository fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public Optional<String> getCoverageJson(String projectId) {
        try {
            return fileManager.getCoverageJson(projectId);
        } catch (Exception e) {
            throw new FileManagerException("Error fetching coverage.json file for project: " + projectId, e);
        }
    }
}
