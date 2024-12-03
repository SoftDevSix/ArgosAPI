package com.softdevsix.domain.entities.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class File {
    private UUID fileId;
    private String fileName;
    private String path;
    private int lineCode;
    private FileCoverageResult coverageResult;
}
