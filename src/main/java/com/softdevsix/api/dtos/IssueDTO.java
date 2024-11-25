package com.softdevsix.api.dtos;

import com.softdevsix.api.types.IssueType;
import com.softdevsix.api.types.SeverityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing an issue detected during code analysis.
 * This DTO contains the details of an issue including its line number, index,
 * a descriptive message, issue type, and severity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {

    /**
     * The line number where the issue was found in the code.
     */
    private int lineNumber;

    /**
     * The index number of the issue in the file or within a sequence of issues.
     */
    private int indexNumber;

    /**
     * A message describing the issue found in the code.
     */
    private String message;

    /**
     * The type of issue detected (e.g., test issue, quality issue).
     */
    private IssueType issueType;

    /**
     * The severity of the issue (e.g., low, medium, high).
     */
    private SeverityType severityType;
}
