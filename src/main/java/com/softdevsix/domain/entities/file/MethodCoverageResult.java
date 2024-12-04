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
@Table(name = "method_coverage_result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodCoverageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "method_coverage_id")
    private UUID id;

    @Column(name = "coverage_percentage")
    private float coveragePercentage;

    @ElementCollection
    @CollectionTable(name = "method_statements",
            joinColumns = @JoinColumn(name = "method_coverage_id"))
    @Column(name = "statement_executed")
    private Map<Integer, Boolean> methodStatements;

    @ManyToOne()
    @JoinColumn(name = "coverage_result_id")
    private FileCoverageResult fileCoverageResult;
}
