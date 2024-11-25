package com.softdevsix.domain.files;

import java.time.LocalDateTime;
import java.util.UUID;

import com.softdevsix.domain.types.IssueType;
import com.softdevsix.domain.types.SeverityType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Issue {
    private final UUID id;
    private final int lineNumber;
    private final int index;
    private final String message;
    private final IssueType type;
    private final SeverityType severity;
    private final FileCoverage fileCoverage;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
