package com.softdevsix.api.repositories.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Repository
public class FileManagerClientRepository implements IFileManagerClientRepository{

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public FileManagerClientRepository(RestTemplate restTemplate, @Value("${filemanager.api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public List<String> listFiles(String projectId) {
        String url = baseUrl + "/api/files?projectId=" + projectId;
        return restTemplate.getForObject(url, List.class);
    }

    @Override
    public String getFileContent(String projectId, String filePath) {
        String url = baseUrl + "/api/file?projectId=" + projectId + "&filePath=" + filePath;
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public Optional<String> getCoverageJson(String projectId) {
        List<String> files = listFiles(projectId);
        String coveragePath = files.stream()
                .filter(file -> file.contains("coverage.json"))
                .findFirst()
                .orElse(null);

        if (coveragePath != null) {
            return Optional.of(getFileContent(projectId, coveragePath));
        }
        return Optional.empty();
    }
}
