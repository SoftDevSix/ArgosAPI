package com.argos.apirest.dtos;

import java.time.LocalDateTime;

public class PullRequestCoverageDTO {
    private String pullRequestId;
    private int totalAnalyzedFiles;
    private boolean status;
    private float coveragePercentage;
    private float requiredCoveragePercentage;
    private String codeRating;
    private String requiredCodeRating;
    private LocalDateTime analysisDate;

    public String getPullRequestId() {
        return pullRequestId;
    }

    public void setPullRequestId(String pullRequestId) {
        this.pullRequestId = pullRequestId;
    }

    public int getTotalAnalyzedFiles() {
        return totalAnalyzedFiles;
    }

    public void setTotalAnalyzedFiles(int totalAnalyzedFiles) {
        this.totalAnalyzedFiles = totalAnalyzedFiles;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getCoveragePercentage() {
        return coveragePercentage;
    }

    public void setCoveragePercentage(float coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }

    public float getRequiredCoveragePercentage() {
        return requiredCoveragePercentage;
    }

    public void setRequiredCoveragePercentage(float requiredCoveragePercentage) {
        this.requiredCoveragePercentage = requiredCoveragePercentage;
    }

    public String getCodeRating() {
        return codeRating;
    }

    public void setCodeRating(String codeRating) {
        this.codeRating = codeRating;
    }

    public String getRequiredCodeRating() {
        return requiredCodeRating;
    }

    public void setRequiredCodeRating(String requiredCodeRating) {
        this.requiredCodeRating = requiredCodeRating;
    }

    public LocalDateTime getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }
}
