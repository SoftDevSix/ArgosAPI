package com.argos.apirest.entities;

import com.argos.apirest.types.CoverageStatus;
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
     * Constructor to initialize a FileCoverage entity with all required properties.
     *
     * @param fileCoverageId The unique identifier for the file coverage record.
     * @param status The coverage status (e.g., PASSED or FAILED).
     * @param linesAnalyzed The number of analyzed lines.
     * @param totalLines The total number of lines in the file.
     * @param coveragePercentage The overall coverage percentage of the file.
     * @param coverageGeneral The general coverage percentage of the file.
     * @param lineCoverage The line coverage percentage of the file.
     * @param methodCoverage The method coverage percentage of the file.
     * @param classCoverage The class coverage percentage of the file.
     * @param fileId The file associated with this coverage.
     */
    public FileCoverage(UUID fileCoverageId, CoverageStatus status, int totalLines, float coveragePercentage,
                        float coverageGeneral, float lineCoverage, float methodCoverage, float classCoverage, File fileId) {
        this.fileCoverageId = fileCoverageId;
        this.status = status;
        this.totalLines = totalLines;
        this.coveragePercentage = coveragePercentage;
        this.coverageGeneral = coverageGeneral;
        this.lineCoverage = lineCoverage;
        this.methodCoverage = methodCoverage;
        this.classCoverage = classCoverage;
        this.fileId = fileId;
    }

    /**
     * Default constructor for the FileCoverage entity.
     */
    public FileCoverage() {}
}
