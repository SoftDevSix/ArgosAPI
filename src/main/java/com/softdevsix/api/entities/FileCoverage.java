package com.softdevsix.api.entities;

import com.softdevsix.api.types.CoverageStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Entity representing the coverage details for a specific file in a pull request.
 * This entity contains information about the file's coverage, including the
 * percentage of lines covered, status, and the associated file.
 */
@Setter
@Getter
@Entity
public class FileCoverage {

    /**
     * The unique identifier for the file coverage record.
     */
    @Id
    @GeneratedValue
    private UUID fileCoverageId;

    /**
     * The coverage status for the file (e.g., PASSED or FAILED).
     */
    @Enumerated(EnumType.STRING)
    private CoverageStatus status;

    /**
     * The total number of lines in the file.
     */
    private int totalLines;

    /**
     * The percentage of the file that is covered by tests.
     */
    private float coveragePercentage;

    /**
     * The general coverage percentage of the file.
     */
    private float coverageGeneral;

    /**
     * The line coverage percentage of the file.
     */
    private float lineCoverage;

    /**
     * The method coverage percentage of the file.
     */
    private float methodCoverage;

    /**
     * The class coverage percentage of the file.
     */
    private float classCoverage;

    /**
     * The file associated with this coverage record.
     * This creates a relationship between the coverage record and the file.
     */
    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File fileId;

    /**
     * Default constructor for the FileCoverage entity.
     */
    public FileCoverage() {
        // Intentionally left empty for JPA use.
    }
}
