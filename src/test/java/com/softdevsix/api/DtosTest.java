package com.softdevsix.api;

import com.softdevsix.api.types.IssueType;
import com.softdevsix.api.types.SeverityType;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import com.softdevsix.api.dtos.*;
import static org.junit.jupiter.api.Assertions.*;

class DtosTest {

    @Test
    void testFileCoverageDTO() {
        boolean status = true;
        int totalLines = 150;
        float coveragePercentage = 85.5f;
        List<IssueDTO> issues = List.of(new IssueDTO(42, 1, "NullPointerException possible", IssueType.DUPLICATE_CODE, SeverityType.HIGH));
        float coverageGeneral = 80.0f;
        float lineCoverage = 85.0f;
        float methodCoverage = 70.0f;
        float classCoverage = 90.0f;

        // Test parameterized constructor
        FileCoverageDTO fileCoverageDTO = new FileCoverageDTO();
        fileCoverageDTO.setStatus(status);
        fileCoverageDTO.setTotalLines(totalLines);
        fileCoverageDTO.setCoveragePercentage(coveragePercentage);
        fileCoverageDTO.setIssues(issues);
        fileCoverageDTO.setCoverageGeneral(coverageGeneral);
        fileCoverageDTO.setLineCoverage(lineCoverage);
        fileCoverageDTO.setMethodCoverage(methodCoverage);
        fileCoverageDTO.setClassCoverage(classCoverage);

        assertTrue(fileCoverageDTO.isStatus());
        assertEquals(totalLines, fileCoverageDTO.getTotalLines());
        assertEquals(coveragePercentage, fileCoverageDTO.getCoveragePercentage());
        assertEquals(issues, fileCoverageDTO.getIssues());
        assertEquals(coverageGeneral, fileCoverageDTO.getCoverageGeneral());
        assertEquals(lineCoverage, fileCoverageDTO.getLineCoverage());
        assertEquals(methodCoverage, fileCoverageDTO.getMethodCoverage());
        assertEquals(classCoverage, fileCoverageDTO.getClassCoverage());
    }

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
