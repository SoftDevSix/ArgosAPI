package com.softdevsix.api.client;

import com.softdevsix.api.controllers.client.CoverageController;
import com.softdevsix.api.services.report.IReportService;
import com.softdevsix.api.services.client.ICoverageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.doThrow;

public class CoverageControllerTest {

    @Test
    void testGetCoverageFileFileExists() {
        ICoverageService coverageService = Mockito.mock(ICoverageService.class);
        IReportService reportService = Mockito.mock(IReportService.class);
        CoverageController controller = new CoverageController(coverageService, reportService);

        String projectId = "test-project";
        String expectedContent = "{\"coverage\":90}";

        when(coverageService.getCoverageJson(projectId)).thenReturn(Optional.of(expectedContent));

        doNothing().when(reportService).processAndSaveReport(anyString());

        ResponseEntity<String> response = controller.processCoverageFile(projectId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Report processed and saved successfully.", response.getBody());

        verify(coverageService).getCoverageJson(projectId);
        verify(reportService).processAndSaveReport(expectedContent);
    }

    @Test
    void testGetCoverageFileFileNotFound() {

        ICoverageService coverageService = Mockito.mock(ICoverageService.class);
        IReportService reportService = Mockito.mock(IReportService.class);
        CoverageController controller = new CoverageController(coverageService, reportService);

        String projectId = "test-project";

        when(coverageService.getCoverageJson(projectId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = controller.processCoverageFile(projectId);

        assertEquals(404, response.getStatusCodeValue());

        verify(coverageService).getCoverageJson(projectId);
        verify(reportService, never()).processAndSaveReport(anyString());
    }

    @Test
    void testGetCoverageFileProcessingError() {

        ICoverageService coverageService = Mockito.mock(ICoverageService.class);
        IReportService reportService = Mockito.mock(IReportService.class);
        CoverageController controller = new CoverageController(coverageService, reportService);

        String projectId = "test-project";
        String expectedContent = "{\"coverage\":90}";

        when(coverageService.getCoverageJson(projectId)).thenReturn(Optional.of(expectedContent));

        doThrow(new RuntimeException("Error processing report")).when(reportService).processAndSaveReport(anyString());

        ResponseEntity<String> response = controller.processCoverageFile(projectId);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Error processing report: Error processing report", response.getBody());

        verify(coverageService).getCoverageJson(projectId);
        verify(reportService).processAndSaveReport(expectedContent);
    }
}
