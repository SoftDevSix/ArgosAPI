package com.softdevsix.domain.entities.file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

class FileCoverageResultTest {

    @Test
    void testFileCoverageResultBuilderAndDefaults() {
        UUID id = UUID.randomUUID();
        float methodCoverage = 75.5f;
        float overallCoverage = 80.0f;

        FileCoverageResult result = FileCoverageResult.builder()
                .id(id)
                .methodCoveragePercentage(methodCoverage)
                .coveragePercentage(overallCoverage)
                .build();

        assertEquals(id, result.getId());
        assertEquals(methodCoverage, result.getMethodCoveragePercentage());
        assertEquals(overallCoverage, result.getCoveragePercentage());
        assertNotNull(result.getAllStatements());
        assertTrue(result.getAllStatements().isEmpty());
    }

    @Test
    void testAddMethodCoverageResult() {
        FileCoverageResult result = FileCoverageResult.builder().build();
        MethodCoverageResult methodCoverage = MethodCoverageResult.builder()
                .id(UUID.randomUUID())
                .coveragePercentage(90.0f)
                .build();

        result.getAllStatements().add(methodCoverage);

        assertEquals(1, result.getAllStatements().size());
        assertEquals(methodCoverage, result.getAllStatements().get(0));
    }
}
