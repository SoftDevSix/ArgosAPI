package com.softdevsix.api;
import com.softdevsix.api.entities.*;
import com.softdevsix.api.types.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EntitiesTest {

    @Test
    void testFileEntity() {
        UUID fileId = UUID.randomUUID();
        String path = "src/main/File.java";
        String content = "public class File {}";
        String language = "Java";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        Project project = new Project();

        File file = new File(fileId, path, content, language, createdAt, updatedAt, project);

        assertEquals(fileId, file.getFileId());
        assertEquals(path, file.getPath());
        assertEquals(content, file.getContent());
        assertEquals(language, file.getLanguage());
        assertEquals(createdAt, file.getCreatedAt());
        assertEquals(updatedAt, file.getUpdatedAt());
        assertEquals(project, file.getProject());

        File fileDefault = new File();
        fileDefault.setFileId(fileId);
        fileDefault.setPath(path);
        fileDefault.setContent(content);
        fileDefault.setLanguage(language);
        fileDefault.setCreatedAt(createdAt);
        fileDefault.setUpdatedAt(updatedAt);
        fileDefault.setProject(project);

        assertEquals(fileId, fileDefault.getFileId());
        assertEquals(path, fileDefault.getPath());
        assertEquals(content, fileDefault.getContent());
        assertEquals(language, fileDefault.getLanguage());
        assertEquals(createdAt, fileDefault.getCreatedAt());
        assertEquals(updatedAt, fileDefault.getUpdatedAt());
        assertEquals(project, fileDefault.getProject());
    }

    @Test
    void testProjectEntity() {
        UUID projectId = UUID.randomUUID();
        String name = "Test Project";
        String repositoryUrl = "https://github.com/example/project";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        List<PullRequest> pullRequests = List.of(new PullRequest());

        Project project = new Project(projectId, name, repositoryUrl, createdAt, updatedAt, pullRequests);

        assertEquals(projectId, project.getProjectId());
        assertEquals(name, project.getName());
        assertEquals(repositoryUrl, project.getRepositoryUrl());
        assertEquals(createdAt, project.getCreatedAt());
        assertEquals(updatedAt, project.getUpdatedAt());
        assertEquals(pullRequests, project.getPullRequests());
    }

    @Test
    void testPullRequestEntity() {
        UUID pullRequestId = UUID.randomUUID();
        String title = "PR Title";
        String description = "PR Description";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        Project project = new Project();

        PullRequest pullRequest = new PullRequest(pullRequestId, title, description, createdAt, updatedAt, project);

        assertEquals(pullRequestId, pullRequest.getPullRequestId());
        assertEquals(title, pullRequest.getTitle());
        assertEquals(description, pullRequest.getDescription());
        assertEquals(createdAt, pullRequest.getCreatedAt());
        assertEquals(updatedAt, pullRequest.getUpdatedAt());
        assertEquals(project, pullRequest.getProject());
    }

    @Test
    void testCoverageEntity() {
        UUID coverageId = UUID.randomUUID();
        int totalFiles = 10;
        float overallCoverage = 85.0f;
        float requiredCoverage = 90.0f;
        String codeRating = "A";
        String requiredRating = "B";
        LocalDateTime analysisDate = LocalDateTime.now();
        CoverageStatus status = CoverageStatus.PASSED;
        PullRequest pullRequest = new PullRequest();

        Coverage coverage = new Coverage();
        coverage.setCoverageId(coverageId);
        coverage.setTotalAnalyzedFiles(totalFiles);
        coverage.setOverallCoveragePercentage(overallCoverage);
        coverage.setRequiredCoveragePercentage(requiredCoverage);
        coverage.setCodeRating(codeRating);
        coverage.setRequiredRating(requiredRating);
        coverage.setAnalysisDate(analysisDate);
        coverage.setOverallStatus(status);
        coverage.setPullRequest(pullRequest);

        assertEquals(coverageId, coverage.getCoverageId());
        assertEquals(totalFiles, coverage.getTotalAnalyzedFiles());
        assertEquals(overallCoverage, coverage.getOverallCoveragePercentage());
        assertEquals(requiredCoverage, coverage.getRequiredCoveragePercentage());
        assertEquals(codeRating, coverage.getCodeRating());
        assertEquals(requiredRating, coverage.getRequiredRating());
        assertEquals(analysisDate, coverage.getAnalysisDate());
        assertEquals(status, coverage.getOverallStatus());
        assertEquals(pullRequest, coverage.getPullRequest());
    }

    @Test
    void testFileCoverageEntity() {
        UUID fileCoverageId = UUID.randomUUID();
        CoverageStatus status = CoverageStatus.PASSED;
        int totalLines = 200;
        float coveragePercentage = 80.0f;
        float generalCoverage = 85.0f;
        float lineCoverage = 90.0f;
        float methodCoverage = 75.0f;
        float classCoverage = 95.0f;
        File file = new File();

        FileCoverage fileCoverage = new FileCoverage();
        fileCoverage.setFileCoverageId(fileCoverageId);
        fileCoverage.setStatus(status);
        fileCoverage.setTotalLines(totalLines);
        fileCoverage.setCoveragePercentage(coveragePercentage);
        fileCoverage.setCoverageGeneral(generalCoverage);
        fileCoverage.setLineCoverage(lineCoverage);
        fileCoverage.setMethodCoverage(methodCoverage);
        fileCoverage.setClassCoverage(classCoverage);
        fileCoverage.setFileId(file);

        assertEquals(fileCoverageId, fileCoverage.getFileCoverageId());
        assertEquals(status, fileCoverage.getStatus());
        assertEquals(totalLines, fileCoverage.getTotalLines());
        assertEquals(coveragePercentage, fileCoverage.getCoveragePercentage());
        assertEquals(generalCoverage, fileCoverage.getCoverageGeneral());
        assertEquals(lineCoverage, fileCoverage.getLineCoverage());
        assertEquals(methodCoverage, fileCoverage.getMethodCoverage());
        assertEquals(classCoverage, fileCoverage.getClassCoverage());
        assertEquals(file, fileCoverage.getFileId());
    }

    @Test
    void testIssueEntity() {
        UUID issueId = UUID.randomUUID();
        int lineNumber = 42;
        int index = 5;
        String message = "NullPointerException possible";
        IssueType issueType = IssueType.DUPLICATE_CODE;
        SeverityType severityType = SeverityType.HIGH;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        FileCoverage fileCoverage = new FileCoverage();

        Issue issue = new Issue();
        issue.setIssueId(issueId);
        issue.setLineNumber(lineNumber);
        issue.setIndex(index);
        issue.setMessage(message);
        issue.setIssueType(issueType);
        issue.setSeverityType(severityType);
        issue.setCreatedAt(createdAt);
        issue.setUpdatedAt(updatedAt);
        issue.setFileCoverage(fileCoverage);

        assertEquals(issueId, issue.getIssueId());
        assertEquals(lineNumber, issue.getLineNumber());
        assertEquals(index, issue.getIndex());
        assertEquals(message, issue.getMessage());
        assertEquals(issueType, issue.getIssueType());
        assertEquals(severityType, issue.getSeverityType());
        assertEquals(createdAt, issue.getCreatedAt());
        assertEquals(updatedAt, issue.getUpdatedAt());
        assertEquals(fileCoverage, issue.getFileCoverage());
    }
}
