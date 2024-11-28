package com.softdevsix.api.mappers.responses;

import com.softdevsix.api.domain.entities.file.FileCoverageResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private UUID fileId;
    private String fileName;
    private String path;
    private int codeLines;
    private FileCoverageResult fileCoverage;
}
