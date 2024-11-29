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

    private static final String FILES_ENDPOINT = "/files";
    private static final String FILE_ENDPOINT = "/file";
    private static final String PROJECT_ID_PARAM = "projectId";
    private static final String FILE_PATH_PARAM = "filePath";
    private static final String COVERAGE_JSON_FILENAME = "coverage.json";

    public FileManagerClientRepository(RestTemplate restTemplate,
                                       @Value("${file.manager.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public List<String> listFiles(String projectId) {
        String url = baseUrl + FILES_ENDPOINT + "?" + PROJECT_ID_PARAM + "=" + projectId;
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
        String url = baseUrl + FILE_ENDPOINT + "?" + PROJECT_ID_PARAM + "="
                + projectId + "&" + FILE_PATH_PARAM + "=" + filePath;
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public Optional<String> getCoverageJson(String projectId) {
        List<String> files = listFiles(projectId);
        String coveragePath = files.stream()
                .filter(file -> file.contains(COVERAGE_JSON_FILENAME))
                .findFirst()
                .orElse(null);

        if (coveragePath != null) {
            return Optional.of(getFileContent(projectId, coveragePath));
        }
        return Optional.empty();
    }
}
