package com.softdevsix.domain.entities.project;

import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "project_results")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResults {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_results_id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_coverage_result_id", referencedColumnName = "id")
    private ProjectCoverageResult coverageResult;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "code_analysis_result_id", referencedColumnName = "id")
    private CodeAnalysisResult codeAnalysisResult;

    @OneToOne(mappedBy = "projectResults")
    private Project project;
}
