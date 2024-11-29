package com.softdevsix.api.notification;

import com.softdevsix.api.controllers.CoverageNotificationController;
import com.softdevsix.api.repositories.client.IFileManagerClientRepository;
import com.softdevsix.api.services.report.IReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyString;

public class CoverageNotificationControllerTest {

    @Test
    void testNotifyProjectCreationSuccess() {
        IFileManagerClientRepository fileManagerClient = Mockito.mock(IFileManagerClientRepository.class);
        IReportService reportService = Mockito.mock(IReportService.class);

        CoverageNotificationController controller = new CoverageNotificationController(fileManagerClient, reportService);

        String projectId = "test-project";
        String coverageJson = "{\"coverage\":90}";

        when(fileManagerClient.getCoverageJson(projectId)).thenReturn(Optional.of(coverageJson));
        doNothing().when(reportService).processAndSaveReport(coverageJson);

        ResponseEntity<String> response = controller.notifyProjectCreation(projectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Coverage report processed successfully for project: " + projectId, response.getBody());

        verify(fileManagerClient).getCoverageJson(projectId);
        verify(reportService).processAndSaveReport(coverageJson);
    }

    @Test
    void testNotifyProjectCreationNotFound() {
        IFileManagerClientRepository fileManagerClient = Mockito.mock(IFileManagerClientRepository.class);
        IReportService reportService = Mockito.mock(IReportService.class);

        CoverageNotificationController controller = new CoverageNotificationController(fileManagerClient, reportService);

        String projectId = "test-project";

        when(fileManagerClient.getCoverageJson(projectId)).thenReturn(null);

        ResponseEntity<String> response = controller.notifyProjectCreation(projectId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Coverage JSON not found for project: " + projectId, response.getBody());

        verify(fileManagerClient).getCoverageJson(projectId);
        verify(reportService, never()).processAndSaveReport(anyString());
    }

    @Test
    void testNotifyProjectCreationError() {
        IFileManagerClientRepository fileManagerClient = Mockito.mock(IFileManagerClientRepository.class);
        IReportService reportService = Mockito.mock(IReportService.class);

        CoverageNotificationController controller = new CoverageNotificationController(fileManagerClient, reportService);

        String projectId = "test-project";

        when(fileManagerClient.getCoverageJson(projectId)).thenThrow(new RuntimeException("FileManager error"));

        ResponseEntity<String> response = controller.notifyProjectCreation(projectId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error processing coverage report: FileManager error", response.getBody());

        verify(fileManagerClient).getCoverageJson(projectId);
        verify(reportService, never()).processAndSaveReport(anyString());
    }
}
