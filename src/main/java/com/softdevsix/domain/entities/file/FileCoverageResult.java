package com.softdevsix.domain.entities.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Setter
@Getter
public class FileCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID fileId;
    private float methodCoveragePercentage;
    private float coveragePercentage;
    @Builder.Default
    private List<MethodCoverageResult> allStatements = new ArrayList<>();
}
