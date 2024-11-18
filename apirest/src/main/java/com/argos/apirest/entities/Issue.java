package com.argos.apirest.entities;

import com.argos.apirest.types.IssueType;
import com.argos.apirest.types.SeverityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Entity
public class Issue {

    @Id
    @GeneratedValue
    private UUID issueId;

    private int lineNumber;
    private int index;
    private String message;

    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Enumerated(EnumType.STRING)
    private SeverityType severityType;

    @ManyToOne
    @JoinColumn(name = "file_coverage_id", nullable = false)
    private FileCoverage fileCoverage;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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

    public Issue() {}
}
