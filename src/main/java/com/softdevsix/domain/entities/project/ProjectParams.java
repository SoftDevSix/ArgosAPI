package com.softdevsix.domain.entities.project;

import com.softdevsix.domain.entities.staticanalysis.Rating;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "required_code_rating")
    private Rating requiredCodeRating;

    @OneToOne(mappedBy = "projectParams")
    private Project project;
}
