package com.softdevsix.domain.repositories.client;

import java.util.List;
import java.util.Optional;

public interface IFileManagerClientRepository {
    List<String> listFiles(String projectId);
    String getFileContent(String projectId, String filePath);
    Optional<String> getCoverageJson(String projectId);
}
