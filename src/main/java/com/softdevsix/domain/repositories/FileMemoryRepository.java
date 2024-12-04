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
    private final static Map<UUID, File> files = new HashMap<>();

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
