package com.softdevsix.api.domain.entities.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FileCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private float methodCoveragePercentage;
    private float coveragePercentage;
    @Builder.Default
    private List<MethodCoverageResult> allStatements = new ArrayList<>();
}
