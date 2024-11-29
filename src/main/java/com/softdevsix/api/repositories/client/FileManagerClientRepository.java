package com.softdevsix.api.repositories.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Repository
public class FileManagerClientRepository implements IFileManagerClientRepository{

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public FileManagerClientRepository(RestTemplate restTemplate,
                                       @Value("${file.manager.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public List<String> listFiles(String projectId) {
        String url = baseUrl + "/files?projectId=" + projectId;
        ResponseEntity<List<String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    @Override
    public String getFileContent(String projectId, String filePath) {
        String url = baseUrl + "/file?projectId=" + projectId + "&filePath=" + filePath;
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
