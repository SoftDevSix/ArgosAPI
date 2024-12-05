package com.softdevsix.application.services;

import com.softdevsix.application.dto.FileCoverageDto;
import com.softdevsix.application.mappers.json.FileMapper;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.exceptions.FileNotFoundException;
import com.softdevsix.domain.repositories.IFileRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class FileService implements IFileService {
    IFileRepository fileRepository;

    @Override
    public FileCoverageDto getFileById(UUID fileId) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        FileCoverageDto fileDto = FileMapper.FileToFileDto(file);
        return fileDto;
    }

    @Override
    public List<File> getAll() {
        return fileRepository.findAll();
    }

}
