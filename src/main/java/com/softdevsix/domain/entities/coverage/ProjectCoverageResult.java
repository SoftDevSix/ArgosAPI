package com.softdevsix.domain.entities.coverage;

import com.softdevsix.domain.entities.project.ProjectResults;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "project_coverage_result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCoverageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_coverage_result_id")
    private UUID id;

    @Column(name = "total_coverage")
    private float totalCoverage;

    @OneToOne(mappedBy = "coverageResult")
    private ProjectResults projectResults;
}
