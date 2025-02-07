package com.softdevsix.domain.entities.coverage;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProjectCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private float requiredCoverage;
    private float totalCoverage;
}
