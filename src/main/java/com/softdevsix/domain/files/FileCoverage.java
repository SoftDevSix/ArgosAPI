package com.softdevsix.domain.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.softdevsix.domain.types.CoverageStatus;

@Getter
@Setter
@AllArgsConstructor
public class FileCoverage {
    private final UUID id;
    private final int linesCovered;
    private CoverageStatus status;
    private final int totalLines;
    private final float coveragePercentage;
    private final float coverageGeneral;
    private final float lineCoverage;
    private final float methodCoverage;
    private final float classCoverage;
    private final File fileId;
}
