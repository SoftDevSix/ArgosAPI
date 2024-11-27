package com.softdevsix.api.domain.staticanalysis;

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
    private char expectedRating;
    private char actualRating;
}
