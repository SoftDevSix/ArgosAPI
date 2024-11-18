package com.argos.apirest.entities;

import com.argos.apirest.types.IssueType;
import com.argos.apirest.types.SeverityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing an issue found in a file during coverage analysis.
 * The issue is associated with a specific file coverage and contains details
 * such as the line number, index, message, and severity of the issue.
 */
@Getter
@Setter
@Entity
public class Issue {

    /**
     * The unique identifier for the issue.
     */
    @Id
    @GeneratedValue
    private UUID issueId;

    /**
     * The line number in the file where the issue was found.
     */
    private int lineNumber;

    /**
     * The index of the issue, which could represent its position in the file.
     */
    private int index;

    /**
     * A descriptive message about the issue found in the code.
     */
    private String message;

    /**
     * The type of the issue (e.g., TEST_ISSUE, QUALITY_ISSUE).
     */
    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    /**
     * The severity of the issue (e.g., LOW, MEDIUM, HIGH, CRITICAL).
     */
    @Enumerated(EnumType.STRING)
    private SeverityType severityType;

    /**
     * The file coverage this issue is associated with.
     * This creates a relationship between the issue and the file coverage record.
     */
    @ManyToOne
    @JoinColumn(name = "file_coverage_id", nullable = false)
    private FileCoverage fileCoverage;

    /**
     * The timestamp when the issue was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the issue was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Constructor to initialize an Issue entity with all required properties.
     *
     * @param issueId The unique identifier for the issue.
     * @param lineNumber The line number where the issue was found.
     * @param index The index of the issue.
     * @param message The message describing the issue.
     * @param issueType The type of issue (e.g., TEST_ISSUE or QUALITY_ISSUE).
     * @param severityType The severity of the issue (e.g., LOW, MEDIUM, HIGH, CRITICAL).
     * @param fileCoverage The file coverage associated with this issue.
     * @param createdAt The creation timestamp of the issue.
     * @param updatedAt The last updated timestamp of the issue.
     */
    public Issue(UUID issueId, int lineNumber, int index, String message, IssueType issueType, SeverityType severityType, FileCoverage fileCoverage, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.issueId = issueId;
        this.lineNumber = lineNumber;
        this.index = index;
        this.message = message;
        this.issueType = issueType;
        this.severityType = severityType;
        this.fileCoverage = fileCoverage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Default constructor for the Issue entity.
     */
    public Issue() {}
}
