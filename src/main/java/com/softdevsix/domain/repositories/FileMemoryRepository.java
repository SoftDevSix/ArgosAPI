package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.domain.entities.file.MethodCoverageResult;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class FileMemoryRepository implements IFileRepository {
    private final Map<UUID, File> files;

    public FileMemoryRepository() {
        this.files = new HashMap<>();
        initializeData();
    }

    private void initializeData() {
        MethodCoverageResult method1 = MethodCoverageResult.builder()
                .id(UUID.randomUUID())
                .coveragePercentage(75.0f)
                .statements(Map.of(
                        1, true,
                        2, false,
                        3, true))
                .build();

        MethodCoverageResult method2 = MethodCoverageResult.builder()
                .id(UUID.randomUUID())
                .coveragePercentage(100.0f)
                .statements(Map.of(
                        1, true,
                        2, true))
                .build();

        MethodCoverageResult method3 = MethodCoverageResult.builder()
                .id(UUID.randomUUID())
                .coveragePercentage(50.0f)
                .statements(Map.of(
                        1, false,
                        2, true))
                .build();

        FileCoverageResult coverageResult1 = FileCoverageResult.builder()
                .id(UUID.randomUUID())
                .methodCoveragePercentage(75.0f)
                .coveragePercentage(70.0f)
                .allStatements(List.of(method1, method2))
                .build();

        FileCoverageResult coverageResult2 = FileCoverageResult.builder()
                .id(UUID.randomUUID())
                .methodCoveragePercentage(80.0f)
                .coveragePercentage(72.5f)
                .allStatements(List.of(method2, method3))
                .build();

        FileCoverageResult coverageResult3 = FileCoverageResult.builder()
                .id(UUID.randomUUID())
                .methodCoveragePercentage(90.0f)
                .coveragePercentage(85.0f)
                .allStatements(List.of(method1, method3))
                .build();

        File file1 = File.builder()
                .fileName("File1.java")
                .path("/src/com/softdevsix/File1.java")
                .lineCode(100)
                .coverageResult(coverageResult1)
                .build();

        File file2 = File.builder()
                .fileName("File2.java")
                .path("/src/com/softdevsix/File2.java")
                .lineCode(150)
                .coverageResult(coverageResult2)
                .build();

        File file3 = File.builder()
                .fileName("File3.java")
                .path("/src/com/softdevsix/File3.java")
                .lineCode(200)
                .coverageResult(coverageResult3)
                .build();

        createFile(file1);
        createFile(file2);
        createFile(file3);
    }

    @Override
    public File createFile(File file) {

        file.setFileId(UUID.randomUUID());

        files.put(file.getFileId(), file);
        return file;
    }

    @Override
    public File updateFile(File file) {
        if (file == null || file.getFileId() == null) {
            return null;
        }

        if (!files.containsKey(file.getFileId())) {
            return null;
        }
        files.put(file.getFileId(), file);
        return file;
    }

    @Override
    public File findById(UUID fileId) {
        return files.get(fileId);
    }

    @Override
    public List<File> getAll() {
        return files.values().stream().toList();
    }

    @Override
    public boolean deleteFile(UUID fileId) {
        return files.remove(fileId) != null;
    }
}
