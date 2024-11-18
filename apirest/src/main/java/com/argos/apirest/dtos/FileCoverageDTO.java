package com.argos.apirest.dtos;

import java.util.List;

public class FileCoverageDTO {
    private boolean status;
    private int linesAnalyzed;
    private int totalLines;
    private float coveragePercentage;
    private List<IssueDTO> issues;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getLinesAnalyzed() {
        return linesAnalyzed;
    }

    public void setLinesAnalyzed(int linesAnalyzed) {
        this.linesAnalyzed = linesAnalyzed;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(int totalLines) {
        this.totalLines = totalLines;
    }

    public float getCoveragePercentage() {
        return coveragePercentage;
    }

    public void setCoveragePercentage(float coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }

    public List<IssueDTO> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueDTO> issues) {
        this.issues = issues;
    }
}
