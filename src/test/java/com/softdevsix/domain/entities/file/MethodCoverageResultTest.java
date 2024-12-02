package com.softdevsix.domain.entities.file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import java.util.Map;

class MethodCoverageResultTest {

    @Test
    void testMethodCoverageResultBuilderAndDefaults() {
        UUID id = UUID.randomUUID();
        float coverage = 95.0f;

        MethodCoverageResult methodCoverage = MethodCoverageResult.builder()
                .id(id)
                .coveragePercentage(coverage)
                .build();

        assertEquals(id, methodCoverage.getId());
        assertEquals(coverage, methodCoverage.getCoveragePercentage());
        assertNotNull(methodCoverage.getStatements());
        assertTrue(methodCoverage.getStatements().isEmpty());
    }

    @Test
    void testAddStatement() {
        MethodCoverageResult methodCoverage = MethodCoverageResult.builder().build();

        methodCoverage.getStatements().put(1, true);
        methodCoverage.getStatements().put(2, false);

        Map<Integer, Boolean> statements = methodCoverage.getStatements();

        assertEquals(2, statements.size());
        assertTrue(statements.get(1));
        assertFalse(statements.get(2));
    }
}
