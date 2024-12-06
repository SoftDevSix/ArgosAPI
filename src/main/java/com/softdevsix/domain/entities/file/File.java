package com.softdevsix.domain.entities.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Builder
@Getter
@Setter
public class File {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID fileId;
    private String fileName;
    private String path;
    private int lineCode;
    private FileCoverageResult coverageResult;

}
