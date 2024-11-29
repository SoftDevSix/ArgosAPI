package com.softdevsix.domain.entities.project;

import com.softdevsix.domain.entities.staticanalysis.Rating;
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
    private Rating requiredCodeRating;
}
