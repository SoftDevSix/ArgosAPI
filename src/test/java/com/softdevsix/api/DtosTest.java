package com.softdevsix.api;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import com.softdevsix.application.dtos.FileCoverageDTO;
import com.softdevsix.application.dtos.IssueDTO;
import com.softdevsix.application.dtos.PullRequestCoverageDTO;
import com.softdevsix.domain.types.IssueType;
import com.softdevsix.domain.types.SeverityType;

import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

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
        //fileCoverageDTO.setIssues(issues);
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
        UUID pullRequestId = UUID.randomUUID();
        int totalAnalyzedFiles = 10;
        boolean status = true;
        float coveragePercentage = 80.0f;
        float requiredCoveragePercentage = 80.0f;
        String codeRating = "A";
        String requiredCodeRating = "A";
        Date analysisDate = new Date();

        PullRequestCoverageDTO pullRequestCoverageDTO = new PullRequestCoverageDTO(
                pullRequestId, totalAnalyzedFiles, status, coveragePercentage, requiredCoveragePercentage, codeRating, requiredCodeRating, analysisDate);

        assertEquals(pullRequestId, pullRequestCoverageDTO.pullRequestId());
        assertEquals(totalAnalyzedFiles, pullRequestCoverageDTO.totalAnalyzedFiles());
        assertEquals(status, pullRequestCoverageDTO.status());
        assertEquals(coveragePercentage, pullRequestCoverageDTO.coveragePercentage());
        assertEquals(requiredCoveragePercentage, pullRequestCoverageDTO.requiredCoveragePercentage());
        assertEquals(codeRating, pullRequestCoverageDTO.codeRating());
        assertEquals(requiredCodeRating, pullRequestCoverageDTO.requiredCodeRating());
        assertEquals(analysisDate, pullRequestCoverageDTO.analysisDate());
    }
}
