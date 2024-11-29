package com.softdevsix.api.client;

import com.softdevsix.api.controllers.client.CoverageController;
import com.softdevsix.api.services.client.ICoverageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CoverageControllerTest {

    @Test
    void testGetCoverageFileFileExists() {
        ICoverageService service = Mockito.mock(ICoverageService.class);
        CoverageController controller = new CoverageController(service);

        String projectId = "test-project";
        String expectedContent = "{\"coverage\":90}";

        when(service.getCoverageJson(projectId)).thenReturn(Optional.of(expectedContent));

        ResponseEntity<String> response = controller.getCoverageFile(projectId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedContent, response.getBody());
    }

    @Test
    void testGetCoverageFileFileNotFound() {
        ICoverageService service = Mockito.mock(ICoverageService.class);
        CoverageController controller = new CoverageController(service);

        String projectId = "test-project";

        when(service.getCoverageJson(projectId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = controller.getCoverageFile(projectId);

        assertEquals(404, response.getStatusCodeValue());
    }
}
