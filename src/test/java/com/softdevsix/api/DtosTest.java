package com.softdevsix.api;

import com.softdevsix.api.types.IssueType;
import com.softdevsix.api.types.SeverityType;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import com.softdevsix.api.dtos.*;
import static org.junit.jupiter.api.Assertions.*;

class DtosTest {


    @Test
    void testIssueDTO() {
        int lineNumber = 42;
        int indexNumber = 1;
        String message = "NullPointerException possible";
        IssueType issueType = IssueType.DUPLICATE_CODE;
        SeverityType severityType = SeverityType.HIGH;

        IssueDTO issueDTO = new IssueDTO(lineNumber, indexNumber, message, issueType, severityType);

        assertEquals(lineNumber, issueDTO.getLineNumber());
        assertEquals(indexNumber, issueDTO.getIndexNumber());
        assertEquals(message, issueDTO.getMessage());
        assertEquals(issueType, issueDTO.getIssueType());
        assertEquals(severityType, issueDTO.getSeverityType());
    }

    @Test
    void testPullRequestCoverageDTO() {
        String pullRequestId = "PR12345";
        int totalAnalyzedFiles = 10;
        String overallStatus = "Passed";
        float overallCoveragePercentage = 85.0f;
        float requiredCoveragePercentage = 90.0f;
        String codeRating = "A";
        String requiredRating = "B";
        LocalDateTime analysisDate = LocalDateTime.now();

        PullRequestCoverageDTO pullRequestCoverageDTO = new PullRequestCoverageDTO();
        pullRequestCoverageDTO.setPullRequestId(pullRequestId);
        pullRequestCoverageDTO.setTotalAnalyzedFiles(totalAnalyzedFiles);
        pullRequestCoverageDTO.setOverallStatus(overallStatus);
        pullRequestCoverageDTO.setOverallCoveragePercentage(overallCoveragePercentage);
        pullRequestCoverageDTO.setRequiredCoveragePercentage(requiredCoveragePercentage);
        pullRequestCoverageDTO.setCodeRating(codeRating);
        pullRequestCoverageDTO.setRequiredRating(requiredRating);
        pullRequestCoverageDTO.setAnalysisDate(analysisDate);

        assertEquals(pullRequestId, pullRequestCoverageDTO.getPullRequestId());
        assertEquals(totalAnalyzedFiles, pullRequestCoverageDTO.getTotalAnalyzedFiles());
        assertEquals(overallStatus, pullRequestCoverageDTO.getOverallStatus());
        assertEquals(overallCoveragePercentage, pullRequestCoverageDTO.getOverallCoveragePercentage());
        assertEquals(requiredCoveragePercentage, pullRequestCoverageDTO.getRequiredCoveragePercentage());
        assertEquals(codeRating, pullRequestCoverageDTO.getCodeRating());
        assertEquals(requiredRating, pullRequestCoverageDTO.getRequiredRating());
        assertEquals(analysisDate, pullRequestCoverageDTO.getAnalysisDate());
    }
}
