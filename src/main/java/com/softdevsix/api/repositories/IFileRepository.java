package com.softdevsix.api.repositories;

import com.softdevsix.api.domain.entities.file.File;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface IFileRepository{
    File createFile(File file);
    File updateFile(File file);
    File findById(UUID fileId);
    List<File> getAll();
    boolean deleteFile(UUID fileId);

}