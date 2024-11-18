package com.argos.apirest.dtos;

import com.argos.apirest.types.IssueType;
import com.argos.apirest.types.SeverityType;

public class IssueDTO {
    private int lineNumber;
    private int indexNumber;
    private String message;
    private IssueType issueType;
    private SeverityType severityType;

    public IssueDTO(int lineNumber, int indexNumber, String message, IssueType issueType, SeverityType severityType) {
        this.lineNumber = lineNumber;
        this.indexNumber = indexNumber;
        this.message = message;
        this.issueType = issueType;
        this.severityType = severityType;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getColumnNumber() {
        return indexNumber;
    }

    public void setColumnNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public SeverityType getSeverityType() {
        return severityType;
    }

    public void setSeverityType(SeverityType severityType) {
        this.severityType = severityType;
    }
}
