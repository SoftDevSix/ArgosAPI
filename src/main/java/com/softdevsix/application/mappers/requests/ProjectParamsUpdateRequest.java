package com.softdevsix.application.mappers.requests;

public record ProjectParamsUpdateRequest(
        float requiredCoveragePercentage,
        String requiredCodeRating
) {
}
