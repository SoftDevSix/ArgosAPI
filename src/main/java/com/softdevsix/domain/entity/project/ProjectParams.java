package com.softdevsix.domain.entity.project;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProjectParams {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private float requiredCoveragePercentage;
    private char requiredCodeRating;
}
