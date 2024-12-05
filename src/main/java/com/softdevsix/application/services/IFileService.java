package com.softdevsix.application.services;

import com.softdevsix.application.dto.FileCoverageDto;
import com.softdevsix.domain.entities.file.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFileService {
    FileCoverageDto getFileById(UUID fileId);
    List<File> getAll();
}
