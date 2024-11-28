package com.softdevsix.api.mappers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateRequest {
    private String name;
    private String description;
    private int requiredCoveragePercentage;
    private String requiredCodeRating;
}