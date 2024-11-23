package com.softdevsix.api.entities;

import com.softdevsix.api.types.IssueType;
import com.softdevsix.api.types.SeverityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Issue {

    /**
     * The unique identifier for the issue.
     */
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
    private IssueType issueType;

    /**
     * The severity of the issue (e.g., LOW, MEDIUM, HIGH, CRITICAL).
     */
    private SeverityType severityType;

    /**
     * The file coverage this issue is associated with.
     * This creates a relationship between the issue and the file coverage record.
     */
    private FileCoverage fileCoverage;

    /**
     * The timestamp when the issue was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when the issue was last updated.
     */
    private LocalDateTime updatedAt;
}
