package com.softdevsix.api.domain.entities.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class MethodCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Map<Integer, Boolean> statements;
    @ManyToOne
    @JoinColumn(name = "id")
    private FileCoverageResult fileCoverageResult;
}
