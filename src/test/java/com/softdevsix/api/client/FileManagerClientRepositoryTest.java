package com.softdevsix.api.client;

import com.softdevsix.api.repositories.client.FileManagerClientRepository;
import com.softdevsix.api.repositories.client.IFileManagerClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileManagerClientRepositoryTest {

    @Test
    void testGetCoverageJson() {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        String baseUrl = "http://localhost:8080";
        IFileManagerClientRepository fileManager = new FileManagerClientRepository(restTemplate, baseUrl);

        String projectId = "test-project-id";

        List<String> fileList = List.of(
                "projects/test-project-id/projectFiles/coverage/argos/coverage.json",
                "projects/test-project-id/projectFiles/other/file.txt"
        );

        when(restTemplate.getForObject(baseUrl + "/api/files?projectId=" + projectId, List.class))
                .thenReturn(fileList);

        String expectedContent = "{\"coverage\":90}";
        when(restTemplate.getForObject(baseUrl + "/api/file?projectId=" + projectId +
                "&filePath=projects/test-project-id/projectFiles/coverage/argos/coverage.json", String.class))
                .thenReturn(expectedContent);

        Optional<String> coverageJson = fileManager.getCoverageJson(projectId);
        assertEquals(Optional.of(expectedContent), coverageJson);
    }
}
