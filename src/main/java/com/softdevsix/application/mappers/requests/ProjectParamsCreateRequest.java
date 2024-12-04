package com.softdevsix.application.mappers.requests;

public record ProjectParamsCreateRequest(
        float requiredCoveragePercentage,
        String requiredCodeRating
) {
}
