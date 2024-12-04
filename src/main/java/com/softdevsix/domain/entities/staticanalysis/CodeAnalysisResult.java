package com.softdevsix.domain.entities.staticanalysis;

import com.softdevsix.domain.entities.project.ProjectResults;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "code_analysis_result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeAnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "code_analysis_result_id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "actual_rating", nullable = false)
    private Rating actualRating;

    @OneToOne(mappedBy = "codeAnalysisResult")
    private ProjectResults projectResults;
}
