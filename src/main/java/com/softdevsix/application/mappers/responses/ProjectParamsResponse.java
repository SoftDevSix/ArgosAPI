package com.softdevsix.application.mappers.responses;

import java.util.UUID;

public record ProjectParamsResponse(
    UUID id,
    float requiredCoveragePercentage,
    String requiredCodeRating
) {
}
