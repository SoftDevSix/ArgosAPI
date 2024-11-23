package com.softdevsix.api;

import com.softdevsix.api.controller.CoverageController;
import com.softdevsix.api.dtos.FileCoverageDTO;
import com.softdevsix.api.services.CoverageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoverageController.class)
class CoverageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoverageService coverageService;

    @Test
    void testGetCoverageFileReturnsDTO() throws Exception {
        FileCoverageDTO dto = new FileCoverageDTO("TestFile.java", "80%", "/path/to/file", 100, "80%");
        Mockito.when(coverageService.getFileCoverage("123")).thenReturn(dto);

        mockMvc.perform(get("/coverage/file?fileId=123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fileName").value("TestFile.java"))
                .andExpect(jsonPath("$.coveragePercentage").value("80%"))
                .andExpect(jsonPath("$.pathFile").value("/path/to/file"));
    }

    @Test
    void testGetCoverageFileThrowsException() throws Exception {
        Mockito.when(coverageService.getFileCoverage("123"))
                .thenThrow(new RuntimeException("File not found"));

        mockMvc.perform(get("/coverage/file?fileId=123"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("File not found"));
    }
}
