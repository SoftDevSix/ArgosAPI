package com.softdevsix.api.client;

import com.softdevsix.api.repositories.client.FileManagerClientRepository;
import com.softdevsix.api.repositories.client.IFileManagerClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class FileManagerClientRepositoryTest {

    @Test
    void testGetCoverageJson() {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

        String baseUrl = "http://localhost:8080/fileManager";

        IFileManagerClientRepository fileManager = new FileManagerClientRepository(restTemplate, baseUrl);

        String projectId = "6570409c-44d0-4ca5-b271-fe433a0a290a";

        List<String> fileList = List.of(
                "projects/" + projectId + "/test-jarasm/coverage.json",
                "projects/" + projectId + "/test-jarasm/src/main/java/org/example/Main.java",
                "projects/" + projectId + "/test-jarasm/src/main/java/org/example/Multiplicacion.java",
                "projects/" + projectId + "/test-jarasm/src/main/java/org/example/Number.java",
                "projects/" + projectId + "/test-jarasm/src/main/java/org/example/Resta.java",
                "projects/" + projectId + "/test-jarasm/src/main/java/org/example/Suma.java",
                "projects/" + projectId + "/test-jarasm/src/test/java/SumaProobs/RestaTest.java",
                "projects/" + projectId + "/test-jarasm/src/test/java/SumaProobs/SumaTest.java"
        );

        ResponseEntity<List<String>> filesResponse = new ResponseEntity<>(fileList, HttpStatus.OK);
        when(restTemplate.exchange(
                eq(baseUrl + "/files?projectId=" + projectId),
                eq(HttpMethod.GET),
                isNull(),
                ArgumentMatchers.<ParameterizedTypeReference<List<String>>>any()
        )).thenReturn(filesResponse);

        String expectedContent = "{\"coveragea\":90}";

        ResponseEntity<String> fileContentResponse = new ResponseEntity<>(expectedContent, HttpStatus.OK);

        String coverageFilePath = "test-jarasm/coverage.json";
        when(restTemplate.exchange(
                eq(baseUrl + "/file?projectId=" + projectId + "&filePath=" + coverageFilePath),
                eq(HttpMethod.GET),
                isNull(),
                ArgumentMatchers.<ParameterizedTypeReference<String>>any()
        )).thenReturn(fileContentResponse);

        when(restTemplate.getForObject(
                baseUrl + "/file?projectId=" + projectId + "&filePath=" + coverageFilePath,
                String.class
        )).thenReturn(expectedContent);

        Optional<String> coverageJson = fileManager.getCoverageJson(projectId);

        assertEquals(Optional.of(expectedContent), coverageJson);
    }
}
