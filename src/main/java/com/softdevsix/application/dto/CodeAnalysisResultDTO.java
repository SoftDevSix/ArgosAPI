package com.softdevsix.application.dto;

import com.softdevsix.domain.entities.staticanalysis.Rating;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CodeAnalysisResultDTO {
    Rating expectedRating;
    Rating actualRating;
}
