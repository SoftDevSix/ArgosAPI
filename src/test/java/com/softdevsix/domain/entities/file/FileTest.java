package com.softdevsix.domain.entities.file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

class FileTest {

    @Test
    void testFileBuilderAndGetters() {
        UUID fileId = UUID.randomUUID();
        String fileName = "testFile.java";
        String path = "/src/main/java";
        int lineCode = 120;
        FileCoverageResult coverageResult = FileCoverageResult.builder()
                .id(UUID.randomUUID())
                .methodCoveragePercentage(85.5f)
                .coveragePercentage(90.0f)
                .build();

        File file = File.builder()
                .fileId(fileId)
                .fileName(fileName)
                .path(path)
                .lineCode(lineCode)
                .coverageResult(coverageResult)
                .build();

        assertEquals(fileId, file.getFileId());
        assertEquals(fileName, file.getFileName());
        assertEquals(path, file.getPath());
        assertEquals(lineCode, file.getLineCode());
        assertEquals(coverageResult, file.getCoverageResult());
    }
}