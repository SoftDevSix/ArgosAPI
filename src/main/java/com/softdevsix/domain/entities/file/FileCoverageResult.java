package com.softdevsix.domain.entities.file;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileCoverageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_coverage_id")
    private UUID id;

    private float methodCoveragePercentage;

    private float coveragePercentage;

    private List<MethodCoverageResult> allStatements = new ArrayList<>();
}
