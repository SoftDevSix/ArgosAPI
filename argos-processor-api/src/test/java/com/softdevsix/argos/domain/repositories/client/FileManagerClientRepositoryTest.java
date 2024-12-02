package com.softdevsix.argos.domain.repositories.client;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class FileManagerClientRepositoryTest {

    @Test
    void testGetCoverageJson() {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

        String baseUrl = "http://localhost:8081/fileManager";

        IFileManagerClientRepository fileManager = new FileManagerClientRepository(restTemplate, baseUrl);

        String projectId = "test-project-id";

        List<String> fileList = List.of(
                "projects/test-project-id/projectFiles/coverage/argos/coverage.json",
                "projects/test-project-id/projectFiles/other/file.txt"
        );

        ResponseEntity<List<String>> filesResponse = new ResponseEntity<>(fileList, HttpStatus.OK);
        when(restTemplate.exchange(
                eq(baseUrl + "/files?projectId=" + projectId),
                eq(HttpMethod.GET),
                eq(null),
                any(ParameterizedTypeReference.class)
        )).thenReturn(filesResponse);

        String expectedContent = "{\"coverage\":90}";
        when(restTemplate.getForObject(
                baseUrl + "/file?projectId=" + projectId +
                        "&filePath=projects/test-project-id/projectFiles/coverage/argos/coverage.json",
                String.class
        )).thenReturn(expectedContent);

        Optional<String> coverageJson = fileManager.getCoverageJson(projectId);

        assertEquals(Optional.of(expectedContent), coverageJson);
    }
}
