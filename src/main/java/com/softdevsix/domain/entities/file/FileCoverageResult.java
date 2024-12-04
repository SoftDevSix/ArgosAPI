package com.softdevsix.domain.entities.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "file_coverage_result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileCoverageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_coverage_id")
    private UUID id;

    @Column(name = "coverage_percentage")
    private float coveragePercentage;

    @Column(name = "method_coverage_percentage")
    private float methodCoveragePercentage;

    @OneToMany(mappedBy = "fileCoverageResult", cascade = CascadeType.ALL)
    @JsonIgnore()
    private List<MethodCoverageResult> methodCoverageResults;

    @OneToOne(mappedBy = "fileCoverageResult")
    private File file;
}
