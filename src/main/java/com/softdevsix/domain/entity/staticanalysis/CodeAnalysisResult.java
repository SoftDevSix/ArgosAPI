package com.softdevsix.domain.entity.staticanalysis;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CodeAnalysisResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Rating expectedRating;
    private Rating actualRating;
}
