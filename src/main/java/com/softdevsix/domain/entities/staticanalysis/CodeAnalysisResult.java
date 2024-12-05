package com.softdevsix.domain.entities.staticanalysis;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "actual_rating", nullable = false)
    private String actualRating;

    @OneToOne(mappedBy = "codeAnalysisResult")
    @JsonIgnore
    private ProjectResults projectResults;
}
