package com.softdevsix.domain.entities.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@Data
@Builder
public class MethodCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID fileCoverageResultId;
    private float coveragePercentage;
    @Builder.Default
    private Map<Integer, Boolean> statements = new HashMap<>();

}
