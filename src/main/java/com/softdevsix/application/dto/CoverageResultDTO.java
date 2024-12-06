package com.softdevsix.application.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CoverageResultDTO {
    float requiredCoverage;
    float totalCoverage;
}
