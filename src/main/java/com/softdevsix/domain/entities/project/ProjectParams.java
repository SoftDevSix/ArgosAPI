package com.softdevsix.domain.entities.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "project_params")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectParams {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_params_id")
    private UUID id;

    @Column(name = "required_coverage_percentage")
    private float requiredCoveragePercentage;

    @Column(name = "required_code_rating", nullable = false)
    private String requiredCodeRating;

    @OneToOne(mappedBy = "projectParams")
    private Project project;
}
