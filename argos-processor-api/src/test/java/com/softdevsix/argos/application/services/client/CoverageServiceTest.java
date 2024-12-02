package com.softdevsix.argos.application.services.client;

import com.softdevsix.argos.domain.exceptions.client.FileManagerException;
import com.softdevsix.argos.domain.repositories.client.IFileManagerClientRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class CoverageServiceTest {

    @Test
    void testGetCoverageJsonSuccess() {
        IFileManagerClientRepository fileManager = mock(IFileManagerClientRepository.class);
        ICoverageService coverageService = new CoverageService(fileManager);

        String projectId = "test-project-id";
        String expectedContent = "{\"coverage\":90}";

        when(fileManager.getCoverageJson(projectId)).thenReturn(Optional.of(expectedContent));

        Optional<String> result = coverageService.getCoverageJson(projectId);

        assertTrue(result.isPresent());
        assertEquals(expectedContent, result.get());
        verify(fileManager).getCoverageJson(projectId);
    }

    @Test
    void testGetCoverageJsonNotFound() {
        IFileManagerClientRepository fileManager = mock(IFileManagerClientRepository.class);
        CoverageService coverageService = new CoverageService(fileManager);

        String projectId = "test-project-id";

        when(fileManager.getCoverageJson(projectId)).thenReturn(Optional.empty());

        Optional<String> result = coverageService.getCoverageJson(projectId);

        assertFalse(result.isPresent());
        verify(fileManager).getCoverageJson(projectId);
    }

    @Test
    void testGetCoverageJsonException() {
        IFileManagerClientRepository fileManager = mock(IFileManagerClientRepository.class);
        CoverageService coverageService = new CoverageService(fileManager);

        String projectId = "test-project-id";

        when(fileManager.getCoverageJson(projectId)).thenThrow(new RuntimeException("Error"));

        assertThrows(FileManagerException.class, () -> coverageService.getCoverageJson(projectId));
    }
}
