package com.softdevsix.application.dto;

import com.softdevsix.domain.entities.staticanalysis.Rating;
import lombok.Value;

@Value
public class ProjectParamsRequestDTO {
    String projectName;
    String description;
    boolean projectCoverage;
    float requiredCoveragePercentage;
    boolean projectRating;
    Rating requiredCodeRating;
}
