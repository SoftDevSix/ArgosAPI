package com.softdevsix.api.entities;

import com.softdevsix.api.types.IssueType;
import com.softdevsix.api.types.SeverityType;
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
     * Default constructor for the Issue entity.
     */
    public Issue() {
        // Intentionally left empty for JPA use.
    }
}
