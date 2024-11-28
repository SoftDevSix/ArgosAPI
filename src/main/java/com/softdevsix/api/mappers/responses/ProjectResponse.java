package com.softdevsix.api.mappers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private UUID projectId;
    private String name;
    private String description;
    private int requiredCoveragePercentage;
    private String requiredCodeRating;
    private String status;
}