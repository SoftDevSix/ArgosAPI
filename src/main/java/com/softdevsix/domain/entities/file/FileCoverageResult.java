package com.softdevsix.domain.entities.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FileCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private List<MethodCoverageResult> allStatements;
}
