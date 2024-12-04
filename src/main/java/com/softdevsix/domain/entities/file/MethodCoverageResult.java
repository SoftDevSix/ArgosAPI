package com.softdevsix.domain.entities.file;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodCoverageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "method_coverage_id")
    private UUID id;

    private float coveragePercentage;
    @Builder.Default
    private Map<Integer, Boolean> statements = new HashMap<>();
}
