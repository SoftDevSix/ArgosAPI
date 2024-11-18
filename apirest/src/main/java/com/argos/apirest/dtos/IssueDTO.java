package com.argos.apirest.dtos;

import com.argos.apirest.types.IssueType;
import com.argos.apirest.types.SeverityType;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing an issue detected during code analysis.
 * This DTO contains the details of an issue including its line number, index,
 * a descriptive message, issue type, and severity.
 */
@Getter
@Setter
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

    /**
     * Constructor for IssueDTO.
     *
     * @param lineNumber The line number where the issue was found.
     * @param indexNumber The index number of the issue.
     * @param message A descriptive message explaining the issue.
     * @param issueType The type of issue (e.g., TEST_ISSUE, QUALITY_ISSUE).
     * @param severityType The severity of the issue (e.g., LOW, MEDIUM, HIGH).
     */
    public IssueDTO(int lineNumber, int indexNumber, String message, IssueType issueType, SeverityType severityType) {
        this.lineNumber = lineNumber;
        this.indexNumber = indexNumber;
        this.message = message;
        this.issueType = issueType;
        this.severityType = severityType;
    }
}
