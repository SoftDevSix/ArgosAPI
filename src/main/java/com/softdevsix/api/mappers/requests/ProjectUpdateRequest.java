package com.softdevsix.api.mappers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateRequest {
    private UUID projectId;
    private String name;
    private String description;
    private int requiredCoveragePercentage;
    private String requiredCodeRating;
}